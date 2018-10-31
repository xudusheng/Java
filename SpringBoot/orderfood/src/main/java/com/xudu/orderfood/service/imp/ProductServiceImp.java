package com.xudu.orderfood.service.imp;

import com.xudu.orderfood.dataobject.ProductInfo;
import com.xudu.orderfood.enums.ProductStatus;
import com.xudu.orderfood.repository.ProductInfoRepository;
import com.xudu.orderfood.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;


@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findByProductId(String productId) {
        return productInfoRepository.findByProductId(productId);
    }

    @Override
    public List<ProductInfo> findAllProductShelf() {
        return productInfoRepository.findAllByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }
}
