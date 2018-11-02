package com.xudu.ihappy.api.responseobj;

import com.xudu.ihappy.objects.Episode;
import lombok.Data;

@Data
public class EpisodeResponseObj {
    private String ekey;
    private String md5key;
    private String title;
    private Integer sort;
    private Integer section;

    public EpisodeResponseObj(Episode episode) {
        this.ekey = episode.getEkey();
        this.md5key = episode.getMd5key();
        this.title = episode.getEpisodeTitle();
        this.sort = episode.getSort();
        this.section = episode.getSection();
    }
}
