package com.xudu.orderfood.api;


import com.xudu.orderfood.api.responseobj.Product.CategoryWithAllProductsObj;
import com.xudu.orderfood.api.responseobj.ResponseObj;
import com.xudu.orderfood.dataobject.ProductCategory;
import com.xudu.orderfood.dataobject.ProductInfo;
import com.xudu.orderfood.service.imp.CategoryServiceImp;
import com.xudu.orderfood.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productService;
    @Autowired
    private CategoryServiceImp categoryService;


    @GetMapping(value = "/categorylist")
    public ResponseObj getCategoryList() {
        List<ProductCategory> productCategories = categoryService.findAll();
        return ResponseObj.SUCEESS(productCategories);
    }


    @GetMapping(value = "/all")
    public ResponseObj getAllCategoriesAndProductList() {

        List<ProductCategory> productCategoryList = categoryService.findAll();
        List<ProductInfo> productList = productService.findAllProductShelf();

        Map<Integer, ArrayList<ProductInfo>> categories = new HashMap<>();

        for (ProductCategory category : productCategoryList) {
            categories.put(category.getCategoryType(), new ArrayList<>());
        }

        for(ProductInfo info : productList) {
            ArrayList<ProductInfo> foods = categories.get(info.getCategoryType());
            foods.add(info);

            //reset
        }

        ArrayList<CategoryWithAllProductsObj> resultData = new ArrayList<>();
        for (ProductCategory category : productCategoryList) {
            CategoryWithAllProductsObj cpObj = new CategoryWithAllProductsObj();
            cpObj.setCategoryName(category.getCategoryName());
            cpObj.setCategoryType(category.getCategoryType());
            cpObj.setFoods(categories.get(category.getCategoryType()));
            resultData.add(cpObj);
        }

        return ResponseObj.SUCEESS(resultData);
    }

}
