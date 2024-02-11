package com.example.demo.exception;

import com.example.demo.vos.AjaxResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ControllerAdvice注解是Spring3.2中新增的注解，学名是Controller增强器，作用是给Controller控制器添加统一的操作或处理
 */
@ControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public AjaxResponse customException(CustomException e) {
        if (e.getCode() == ResponseCodeEnum.SYSTEM_ERROR.getCode()) {
            //TODO: 将500异常持久化，方便运维处理
        }
        return AjaxResponse.error(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResponse customException(Exception e) {
        //TODO: 将异常信息持久化，方便运维处理
        return AjaxResponse.error(new CustomException(ResponseCodeEnum.OTHER_ERROR));
    }
}
