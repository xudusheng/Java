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


//    /* 数据错误：50001-599999 */
//    RESULE_DATA_NONE(50001, "数据未找到"),
//    DATA_IS_WRONG(50002, "数据有误"),
//    DATA_ALREADY_EXISTED(50003, "数据已存在"),
    public static <T> ResponseObj RESULE_DATA_NONE() {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setResponseStatus(ResponseStatus.RESULE_DATA_NONE);
        return responseObj;
    }

    public static <T> ResponseObj DATA_IS_WRONG() {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setResponseStatus(ResponseStatus.DATA_IS_WRONG);
        return responseObj;
    }

    public static <T> ResponseObj DATA_ALREADY_EXISTED() {
        ResponseObj responseObj = new ResponseObj();
        responseObj.setResponseStatus(ResponseStatus.DATA_ALREADY_EXISTED);
        return responseObj;
    }

}


