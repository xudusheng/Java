package com.xudu.orderfood.service.imp;

import com.xudu.orderfood.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImpTest {

    @Autowired
    private CategoryServiceImp serviceImp;

    @Test
    public void findByCategoryId() {
        ProductCategory productCategory = serviceImp.findByCategoryId(1);
        if (productCategory == null) {
            log.info("未知分类");
        } else {
            log.info(productCategory.toString());
        }
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoriesList = serviceImp.findAll();
        Assert.assertNotEquals(0, productCategoriesList.size());
        log.info(productCategoriesList.toString());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoriesList = serviceImp.findByCategoryTypeIn(Arrays.asList(1, 2, 10));
        Assert.assertNotEquals(0, productCategoriesList.size());
        log.info(productCategoriesList.toString());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("日本料理");
        productCategory.setCategoryType(2);
        serviceImp.save(productCategory);

        productCategory = new ProductCategory();
        productCategory.setCategoryName("甜点饮品");
        productCategory.setCategoryType(3);
        serviceImp.save(productCategory);

        productCategory = new ProductCategory();
        productCategory.setCategoryName("小吃快餐");
        productCategory.setCategoryType(4);
        serviceImp.save(productCategory);


        productCategory = new ProductCategory();
        productCategory.setCategoryName("香锅烤鱼");
        productCategory.setCategoryType(5);
        serviceImp.save(productCategory);
    }
}