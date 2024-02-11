package com.example.demo.exception;

public enum ResponseCodeEnum {
    OK(200, "success"),
    INPUT_ERROR(400, "input error"),
    SYSTEM_ERROR(500, "System internal error"),
    OTHER_ERROR(999, "unknown error"),
    ;
    private int code;
    private String desc;
    ResponseCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
