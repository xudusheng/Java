package com.xudu.ihappy.api.responseobj;

import com.xudu.ihappy.objects.Video;
import lombok.Data;

@Data
public class VideoResponseObj {

    private String md5key;

    private Integer type;

    private String name;

    private String update_time;

    private String image_src;

    private String hdtag;

    private String director;

    private String casts;

    private String style;

    private String nation;

    private String public_date;

    private String score;

    private String summary;


    public VideoResponseObj(Video video) {
        this.md5key = video.getMd5key();
        this.type = video.getVideoType();
        this.name = video.getVideoName();
        this.update_time = video.getVideoUpdateTime();
        this.image_src = video.getVideoImageSrc();
        this.hdtag = video.getVideoHdtag();
        this.director = video.getVideoDirector();
        this.casts = video.getVideoCasts();
        this.style = video.getVideoStyle();
        this.nation = video.getVideoNation();
        this.public_date = video.getVideoPublicDate();
        this.score = video.getVideoScore();
        this.summary = video.getVideoSummary();
    }
}
