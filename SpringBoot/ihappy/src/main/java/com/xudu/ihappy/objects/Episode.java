package com.xudu.ihappy.objects;

import com.xudu.ihappy.api.responseobj.EpisodeResponseObj;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String episodeId;
    private String ekey;
    private String md5key;
    private String episodeTitle;
    private String episodeHref;
    private Integer sort;
    private Integer section;

    public EpisodeResponseObj toEpisodeResponseObj() {
        return new EpisodeResponseObj(this);
    }
}
