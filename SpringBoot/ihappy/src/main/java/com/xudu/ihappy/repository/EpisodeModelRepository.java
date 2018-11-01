package com.xudu.ihappy.repository;

import com.xudu.ihappy.objects.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeModelRepository extends JpaRepository<Episode, String> {

    public List<Episode> findAllByMd5key(String md5key);

    public Episode findByEkey(String ekey);

}
