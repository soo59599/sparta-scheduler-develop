package com.spartaschedulerdevelop.common.advice.exception;

import com.spartaschedulerdevelop.common.advice.ResponseCode;
import org.springframework.http.HttpStatus;

public class MyCustomException extends RuntimeException {
    private final ResponseCode responseCode;

    public MyCustomException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

    public String getCode() {
        return responseCode.getCode();
    }

    public HttpStatus getHttpStatus(){return responseCode.getHttpStatus();}
}

