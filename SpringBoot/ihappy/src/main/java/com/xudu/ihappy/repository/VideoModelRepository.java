package com.xudu.ihappy.repository;

import com.xudu.ihappy.objects.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface VideoModelRepository extends JpaRepository<Video, Integer>, JpaSpecificationExecutor {

    public Video findByMd5key(String md5key);
    public List<Video> findByVideoType(Integer type);


}
