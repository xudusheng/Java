package com.xudu.ihappy.objects;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer videoId;

    /*video_href进行MD5加密所得*/
    private String md5key;

    private Integer videoType;

    private String videoName;

    private String videoHref;

    private String videoUpdateTime;

    private String videoImageSrc;

    private String videoHdtag;

    private String videoDirector;

    private String videoCasts;

    private String videoStyle;

    private String videoNation;

    private String videoUpdateStatus;

    private String videoScore;

    private String videoSummary;
}
