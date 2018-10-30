package com.xudu.orderfood.service;

import com.xudu.orderfood.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory findByCategoryId(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
