package com.xudu.orderfood.service.imp;

import com.xudu.orderfood.dataobject.ProductCategory;
import com.xudu.orderfood.repository.ProductCategoryRepository;
import com.xudu.orderfood.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Override
    public ProductCategory findByCategoryId(Integer categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
