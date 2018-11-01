package com.xudu.ihappy.services;

import com.xudu.ihappy.objects.Video;
import com.xudu.ihappy.repository.VideoModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoModelRepository videoModelRepository;

    /*根据类型返回数组*/
    public List<Video> findAllWithVideoType(Integer type) {
        List<Video> videoList = videoModelRepository.findByVideoType(type);
        return videoList;
    }

    /*根据md5key搜索视频*/
    public Video findVideoWithMd5key(String md5key) {
        return videoModelRepository.findByMd5key(md5key);
    }

    /*添加、保存视频*/
    public Video saveVideoModel(Video newVideo) {
        return videoModelRepository.save(newVideo);
    }


}
