package com.xudu.orderfood.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Data
public class ProductInfo {

    @Id
    /*商品id*/
    private String productId;
    /*商品名称*/
    private String productName;
    /*商品价格*/
    private BigDecimal productPrice;
    /*商品状态*/
    private Integer productStatus;
    /*商品库存*/
    private Integer productStock;
    /*商品描述*/
    private String productDescription;
    /*商品图片*/
    private String productIcon;
    /*商品所属分类*/
    private Integer categoryType;

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productStatus=" + productStatus +
                ", productStock=" + productStock +
                ", productDescription='" + productDescription + '\'' +
                ", productIcon='" + productIcon + '\'' +
                ", categoryType=" + categoryType +
                '}';
    }
}
