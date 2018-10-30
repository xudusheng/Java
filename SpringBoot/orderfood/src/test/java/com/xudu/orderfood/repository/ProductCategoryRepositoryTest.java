package com.xudu.orderfood.repository;

import com.xudu.orderfood.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;


    @Test
    public void addTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("闽南菜");
        productCategory.setCategoryType(1);
        repository.save(productCategory);

        productCategory = new ProductCategory();
        productCategory.setCategoryName("满汉全席");
        productCategory.setCategoryType(2);
        repository.save(productCategory);


        productCategory = new ProductCategory();
        productCategory.setCategoryName("山东菜");
        productCategory.setCategoryType(3);
        repository.save(productCategory);

        productCategory = new ProductCategory();
        productCategory.setCategoryName("湖北菜");
        productCategory.setCategoryType(4);
        repository.save(productCategory);
    }

    @Test
    public void deleteTest() {
     ProductCategory productCategory = repository.findByCategoryType(1);
     repository.delete(productCategory);
    }

    @Test
    public void updateTest() {
        ProductCategory productCategory = repository.findByCategoryType(1);
        productCategory.setCategoryName("满汉全席");
        repository.save(productCategory);
    }

    @Test
    public void findTest() {
        ProductCategory productCategory = repository.findByCategoryType(1);
        log.info(productCategory.toString());
    }

    @Test
    public void findListTest() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
    }
}