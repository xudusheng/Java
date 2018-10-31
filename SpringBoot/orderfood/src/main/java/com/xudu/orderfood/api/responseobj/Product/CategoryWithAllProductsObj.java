package com.xudu.orderfood.api.responseobj.Product;

import com.xudu.orderfood.dataobject.ProductCategory;
import com.xudu.orderfood.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class CategoryWithAllProductsObj {

    /*类目名称*/

    private String categoryName;

    /*类目类型/编号*/
    private Integer categoryType;

    private List<ProductInfo> foods;


}
