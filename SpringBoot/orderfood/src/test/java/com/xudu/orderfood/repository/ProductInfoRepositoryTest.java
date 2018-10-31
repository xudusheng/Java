package com.xudu.orderfood.repository;

import com.xudu.orderfood.dataobject.ProductInfo;
import com.xudu.orderfood.enums.ProductStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoRepository.findAllByProductStatus(ProductStatus.UP.getCode());
        Assert.assertNotEquals(0, productInfoList.size());
    }

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("001");
        productInfo.setProductName("皮蛋瘦肉粥");
        productInfo.setProductPrice(new BigDecimal(11.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("一碗皮蛋瘦肉粥");
        productInfo.setProductIcon("");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);
        productInfoRepository.save(productInfo);

        productInfo = new ProductInfo();
        productInfo.setProductId("002");
        productInfo.setProductName("兰州拉面");
        productInfo.setProductPrice(new BigDecimal(11.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("一碗兰州拉面");
        productInfo.setProductIcon("");
        productInfo.setProductStatus(1);
        productInfo.setCategoryType(2);

        ProductInfo result = productInfoRepository.save(productInfo);
        log.info(result.toString());
    }
}