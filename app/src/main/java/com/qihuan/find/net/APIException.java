package com.qihuan.find.net;

/**
 * 自定义异常
 */
public class APIException extends RuntimeException {

    private String code;
    private String message;

    public APIException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}