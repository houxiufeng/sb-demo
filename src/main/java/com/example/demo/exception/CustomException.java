package com.example.demo.exception;

public class CustomException extends RuntimeException {
    private int code;
    private String message;
    private CustomException() {}
    public CustomException(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getDesc();
    }

    public CustomException(ResponseCodeEnum responseCodeEnum, String message) {
        this.code = responseCodeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
