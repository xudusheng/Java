package com.xudu.orderfood.enums;


import lombok.Data;
import lombok.Getter;

@Getter
public enum ProductStatus {
    UP(1, "已上架"),
    DOWN(0, "已下架")
    ;


    private Integer code;
    private String message;

    ProductStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
