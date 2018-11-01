package com.xudu.ihappy.api;


import com.xudu.ihappy.Util.VideoUtil;
import com.xudu.ihappy.api.responseobj.ResponseObj;
import com.xudu.ihappy.objects.Video;
import com.xudu.ihappy.services.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/video")
@Slf4j
public class VideoController {

    @Autowired
    private VideoService videoService;


    /*根据类型返回数组*/
    @GetMapping(value = "/videolist")
    public ResponseObj findAllWithVideoType(@RequestParam(value = "type") Integer type) {
        List<Video> videoList = videoService.findAllWithVideoType(type);
        return ResponseObj.SUCEESS(videoList);
    }

    /*根据md5key搜索视频*/
    @GetMapping(value = "/video")
    public ResponseObj findVideoWithMd5key(@RequestParam(value = "md5key") String md5key) {
        Video video = videoService.findVideoWithMd5key(md5key);
        return ResponseObj.SUCEESS(video);
    }


    // 读取的地址
    private final static String ROOT_URL_ADDRESS = "http://www.74zu.com";

    @GetMapping(value = "/crawler")
    public ResponseObj crawlerVideos(@RequestParam(value = "type") Integer type,
                                     @RequestParam(value = "page_no") Integer page_no,
                                     @RequestParam(value = "page_size") Integer page_size) throws Exception {
        type = (type > 1) ? type : 1;
        page_no = (page_no > 1) ? page_no : 1;
        String url_address = ROOT_URL_ADDRESS + "/type/" + type + "/" + page_no + ".html";

        Document document = Jsoup.connect(url_address).userAgent("iOS/12.0.1").get();
        Elements elements = document.select("div.movie-item");

        ArrayList<Video> videoArrayList = new ArrayList<>();
        for (Element element : elements) {
            Element moviename_element = element.select("a.movie-name").first();
            Element img_element = element.select("img").first();
            Element otherinfo_element = element.select("div.otherinfo").first();
            Element hdtag_element = element.select("button.hdtag").first();

            String title = moviename_element.text();
            String detailHref = moviename_element.attr("href");
            String img = img_element.attr("src");
            String update = otherinfo_element.text();
            String hdtag = hdtag_element.text();

            if (detailHref.startsWith(ROOT_URL_ADDRESS) == false) {
                if (detailHref.startsWith("/")) {
                    detailHref = ROOT_URL_ADDRESS + detailHref;
                } else {
                    detailHref = ROOT_URL_ADDRESS + "/" + detailHref;
                }
            }

            Video video = new Video();
            video.setVideoName(title);
            video.setVideoType(type);
            video.setMd5key(VideoUtil.getMD5Str(detailHref));
            video.setVideoImageSrc(img);
            video.setVideoHref(detailHref);
            video.setVideoUpdateTime(update);
            video.setVideoHdtag(hdtag);

            log.info("title={}, detail={}, img={}, update={}, hdtag={}", title, detailHref, img, update, hdtag);
            if (this.saveVideoModel(video)) {
                videoArrayList.add(video);
            }
        }

        return ResponseObj.SUCEESS(videoArrayList);

    }

    /*添加、保存视频*/
    public boolean saveVideoModel(Video newVideo) {
        Video video = videoService.saveVideoModel(newVideo);
        return (video != null);
    }

}
