package com.xudu.orderfood.repository;

import com.xudu.orderfood.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.jvm.hotspot.debugger.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {


    ProductInfo findByProductId(String productId);

    List<ProductInfo> findAllByProductStatus(Integer productStatus);

    Page findAll(Pageable pageable);


}
