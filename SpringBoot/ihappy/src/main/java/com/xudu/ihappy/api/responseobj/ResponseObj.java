package com.xudu.ihappy.api.responseobj;

import lombok.Data;


@Data
public class ResponseObj <T> {

    public Integer code;
    public String msg;
    public T data;

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.code = responseStatus.getCode();
        this.msg = responseStatus.getMessage();
    }

    public static <T> ResponseObj SUCEESS(T mData) {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setResponseStatus(ResponseStatus.SUCCESS);
        responseObj.setData(mData);
        return responseObj;
    }

}


