package com.xudu.orderfood.service;

import com.xudu.orderfood.dataobject.ProductInfo;
import org.springframework.data.domain.PageRequest;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;



public interface ProductService {

    ProductInfo findByProductId(String productId);

    /*查询所有上架商品*/
    List<ProductInfo> findAllProductShelf();

    /*添加商品*/
    ProductInfo save(ProductInfo productInfo);

    //加库存

    //减库存
}
