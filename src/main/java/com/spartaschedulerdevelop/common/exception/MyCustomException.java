package com.spartaschedulerdevelop.common.exception;

import org.springframework.http.HttpStatus;

public class MyCustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public MyCustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public String getCode() {
        return errorCode.getCode();
    }

    public HttpStatus getHttpStatus(){return errorCode.getHttpStatus();}
}

