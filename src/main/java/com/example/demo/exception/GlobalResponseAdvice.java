package com.example.demo.exception;

import com.example.demo.vos.AjaxResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局统一处理返回值
 * 注意：如果controller返回值是string, 默认会调用stringconvert, 所以会报类型转化异常错误
 */
@Component
@ControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice{
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType,
        Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof AjaxResponse) {
            return body;
        } else {
            return AjaxResponse.success(body);
        }
    }

//    private HttpStatus getHttpStatus(Object body) {
//        try {
//            return HttpStatus.valueOf(((AjaxResponse)body).getCode());
//        } catch (IllegalArgumentException e) {
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//    }
}
