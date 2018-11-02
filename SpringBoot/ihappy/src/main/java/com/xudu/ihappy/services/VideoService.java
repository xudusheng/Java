package com.xudu.ihappy.services;

import com.xudu.ihappy.objects.Video;
import com.xudu.ihappy.repository.VideoModelRepository;
import jdk.nashorn.internal.runtime.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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



    public Page<Video> findAllWithVideoTypeAndPage(Integer type, Integer pageNo, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNo, pageSize, Sort.Direction.DESC, "videoUpdateTime");
        Specification<Video> specification = new Specification<Video>() {
            @Override
            public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> predicateArrayList = new ArrayList<Predicate>();
                predicateArrayList.add(criteriaBuilder.equal(root.get("videoType"), type));
                return criteriaBuilder.and(predicateArrayList.toArray(new Predicate[predicateArrayList.size()]));
            }
        };

        Page<Video> videoPage = videoModelRepository.findAll(specification, pageable);
        return videoPage;
    }


//    public Page<Video> findAdmin(String username, String password) {
//        Pageable pageable = new PageRequest.of(1, 2, Sort.Direction.ASC, "id");
//
//        Specification<AdminAccount> specification = new Specification<AdminAccount>() {
//            @Override
//            public Predicate toPredicate(Root<AdminAccount> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
//                List<Predicate> list = new ArrayList<Predicate>();
//
//                if (!StringUtils.isEmpty(username)) {
//                    list.add(cb.like(root.get("username").as(String.class), "%" + username + "%"));
//                }
//
//                if (!StringUtils.isEmpty(password)) {
//                    list.add(cb.equal(root.get("password").as(String.class), password));
//                }
//                return cb.and(list.toArray(new Predicate[list.size()]));
//            }
//        };
//
//
//        Page<AdminAccount> adminAccounts = adminRepository.findAll(specification, pageable);
//
//        return adminAccounts;
//
//    }




    /*根据md5key搜索视频*/
    public Video findVideoWithMd5key(String md5key) {
        return videoModelRepository.findByMd5key(md5key);
    }

    /*添加、保存视频*/
    public Video saveVideoModel(Video newVideo) {
        return videoModelRepository.save(newVideo);
    }

    public List<Video> saveAll(List<Video> all) {
        return videoModelRepository.saveAll(all);
    }
}
