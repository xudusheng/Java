package com.xudu.orderfood.repository;

import com.xudu.orderfood.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {


    ProductInfo findByProductId(String productId);

    List<ProductInfo> findAllByProductStatus(Integer productStatus);



}
