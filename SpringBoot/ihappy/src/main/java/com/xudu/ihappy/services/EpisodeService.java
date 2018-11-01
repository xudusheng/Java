package com.xudu.ihappy.services;

import com.xudu.ihappy.objects.Episode;
import com.xudu.ihappy.repository.EpisodeModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {

    @Autowired
    private EpisodeModelRepository episodeModelRepository;

    public List<Episode> findAllByMd5key(String md5key) {
        return episodeModelRepository.findAllByMd5key(md5key);
    }

    public Episode findByEkey(String ekey) {
        return episodeModelRepository.findByEkey(ekey);
    }

    public Episode save(Episode episode) {
        return episodeModelRepository.save(episode);
    }

    public List<Episode> saveAll(List<Episode> all) {
        return episodeModelRepository.saveAll(all);
    }
}
