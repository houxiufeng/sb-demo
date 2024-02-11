package com.example.demo.vos;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ResponseCodeEnum;
import lombok.Data;

@Data
public class AjaxResponse {

    private int code; //请求响应状态码（200、400、500）
    private String message;  //请求结果描述信息
    private Object data; //请求结果数据（通常用于查询操作）

    private AjaxResponse(){}

    //请求成功的响应，不带查询数据（用于删除、修改、新增接口）
    public static AjaxResponse success(){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(ResponseCodeEnum.OK.getCode());
        ajaxResponse.setMessage(ResponseCodeEnum.OK.getDesc());
        return ajaxResponse;
    }

    //请求成功的响应，带有查询数据（用于数据查询接口）
    public static AjaxResponse success(Object obj){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(ResponseCodeEnum.OK.getCode());
        ajaxResponse.setMessage(ResponseCodeEnum.OK.getDesc());
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }

    //请求成功的响应，带有查询数据（用于数据查询接口）
    public static AjaxResponse success(Object obj,String message){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(ResponseCodeEnum.OK.getCode());
        ajaxResponse.setMessage(message);
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }

    public static AjaxResponse error(CustomException customException){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(customException.getCode());
        ajaxResponse.setMessage(customException.getMessage());
        return ajaxResponse;
    }

    public static AjaxResponse error(ResponseCodeEnum customExceptionType, String message){
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setCode(customExceptionType.getCode());
        ajaxResponse.setMessage(message);
        return ajaxResponse;
    }


}
