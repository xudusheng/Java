package com.xudu.ihappy.repository;

import com.xudu.ihappy.objects.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoModelRepository extends JpaRepository<Video, Integer> {

    public Video findByMd5key(String md5key);
    public List<Video> findByVideoType(Integer type);

}
