package com.xudu.ihappy.api;

import com.xudu.ihappy.Util.VideoUtil;
import com.xudu.ihappy.api.responseobj.EpisodeResponseObj;
import com.xudu.ihappy.api.responseobj.ResponseObj;
import com.xudu.ihappy.api.responseobj.VideoResponseObj;
import com.xudu.ihappy.objects.Episode;
import com.xudu.ihappy.objects.Video;
import com.xudu.ihappy.services.EpisodeService;
import com.xudu.ihappy.services.VideoService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/episode")
public class EpisodeController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private EpisodeService episodeService;

    @GetMapping(value = "/query")
    @PostMapping(value = "/query")
    /* 根据md5key进行查询
     * 如果数据库不存在视频，直接返回错误码
     * 如果存在视频，则开始抓取详情信息，如果详情信息有变化，则刷新视频基本信息
     * 播放列表直接由抓取的结果返回，不走数据库
     * */
    public ResponseObj findAllByMd5key(@RequestParam(value = "md5key") String md5key) throws Exception {
        List<Episode> episodeList = episodeService.findAllByMd5key(md5key);
        if (episodeList.size() < 1) {
            Video video = videoService.findVideoWithMd5key(md5key);
            if (video == null) {
                return ResponseObj.RESULE_DATA_NONE();
            } else {
                episodeList = this.crawlerVideoDetail(video);
            }
        }
        ArrayList<EpisodeResponseObj> episodeResponseObjArrayList = this.convertEpisodeListToEpisodeResponseObjList(episodeList);
        return ResponseObj.SUCEESS(episodeResponseObjArrayList);
    }

    public Episode findByEkey(String ekey) {
        return episodeService.findByEkey(ekey);
    }

    private Episode save(Episode episode) {
        return episodeService.save(episode);
    }


    /*爬取视频详情页*/
    private ArrayList<Episode> crawlerVideoDetail(Video video) throws Exception {
        String detailurl = video.getVideoHref();
        if (detailurl.length() < 1) {
            return new ArrayList<>();
        }

        Video newVideo = new Video();
        newVideo.setVideoId(video.getVideoId());
        newVideo.setVideoName(video.getVideoName());
        newVideo.setVideoType(video.getVideoType());
        newVideo.setMd5key(video.getMd5key());
        newVideo.setVideoImageSrc(video.getVideoImageSrc());
        newVideo.setVideoHref(video.getVideoHref());
        newVideo.setVideoUpdateTime(video.getVideoUpdateTime());
        newVideo.setVideoHdtag(video.getVideoHdtag());

        Document document = Jsoup.connect(detailurl).userAgent("iOS/12.0.1").get();
        Elements elements = document.select("tr");
        for (Element element : elements) {
            String title = element.selectFirst("span.info-label").text();
            String content = element.select("td").last().text();
            switch (title) {
                case "导演":
                    newVideo.setVideoDirector(content);
                    break;
                case "主演":
                    newVideo.setVideoCasts(content);
                    break;
                case "类型":
                    newVideo.setVideoStyle(content);
                    break;
                case "制片国家":
                    newVideo.setVideoNation(content);
                    break;
                case "更新状态":
                    newVideo.setVideoUpdateTime(content);
                    break;
                case "上映日期":
                    newVideo.setVideoPublicDate(content);
                    break;
            }
        }

        String summary = document.selectFirst("p.summary").text();
        newVideo.setVideoSummary(summary);

        if (newVideo.getVideoUpdateTime().equals(video.getVideoUpdateTime()) == false) {
            videoService.saveVideoModel(newVideo);
        }


        ArrayList<Episode> episodeArrayList = new ArrayList<>();
        Elements groups = document.select("ul.dslist-group");
        for (int i = 0; i < groups.size(); i++) {
            Element group = groups.get(i);
            Elements items = group.select("li.dslist-group-item > a");
            for (int j = 0; j < items.size(); j++) {
                Element item = items.get(j);
                String href = item.attr("href");
                String title = item.text();
                String ekey = newVideo.getMd5key() + i + j;

                String root_url = VideoUtil.ROOT_URL_ADDRESS;
                if (href.startsWith(root_url) == false) {
                    if (href.startsWith("/")) {
                        href = root_url + href;
                    } else {
                        href = root_url + "/" + href;
                    }
                }

                Episode episode = new Episode();
                episode.setEkey(ekey);
                episode.setMd5key(newVideo.getMd5key());
                episode.setEpisodeTitle(title);
                episode.setEpisodeHref(href);
                episode.setSection(i);
                episode.setSort(j);
                episodeArrayList.add(episode);

                episodeService.save(episode);
            }
        }

//        episodeService.saveAll(episodeArrayList);
        return episodeArrayList;
    }


    /*转成api需要的字段输出*/
    private ArrayList<EpisodeResponseObj> convertEpisodeListToEpisodeResponseObjList(List<Episode> episodeArrayList) {
        ArrayList<EpisodeResponseObj> episodeResponseObjArrayList = new ArrayList<>();
        for (Episode episode : episodeArrayList) {
            episodeResponseObjArrayList.add(episode.toEpisodeResponseObj());
        }
        return episodeResponseObjArrayList;
    }
}
