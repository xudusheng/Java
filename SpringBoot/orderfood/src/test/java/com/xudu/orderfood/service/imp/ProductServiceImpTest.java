package com.xudu.orderfood.service.imp;

import com.xudu.orderfood.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImpTest {

    @Autowired
    private ProductServiceImp productService;

    @Test
    public void findByProductId() {
        ProductInfo productInfo = productService.findByProductId("001");
        Assert.assertEquals("001", productInfo.getProductId());
    }

    @Test
    public void findAllProductShelf() {
        List<ProductInfo> productInfoList = productService.findAllProductShelf();
        Assert.assertNotEquals(0, productInfoList.size());
    }

//    @Test
//    public void findProductShelf() {
//        PageRequest pageRequest = PageRequest.of(0,2);
//        Page<ProductInfo> pageInfo = productService.findProductShelf(pageRequest);
//        Assert.assertNotEquals(0, pageInfo.getTotalPages());
//    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("002");
        productInfo.setProductName("皮蛋瘦肉粥");
        productInfo.setProductPrice(new BigDecimal(11.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("一碗皮蛋瘦肉粥");
        productInfo.setProductIcon("");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);
        productService.save(productInfo);

        productInfo = new ProductInfo();
        productInfo.setProductId("001");
        productInfo.setProductName("兰州拉面");
        productInfo.setProductPrice(new BigDecimal(11.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("一碗兰州拉面");
        productInfo.setProductIcon("");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);
        ProductInfo result = productService.save(productInfo);

        Assert.assertEquals("002", result.getProductId());
    }
}