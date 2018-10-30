package com.xudu.orderfood.repository;

import com.xudu.orderfood.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    public ProductCategory findByCategoryId(Integer categoryId);
    public ProductCategory findByCategoryType(Integer categoryType);

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
