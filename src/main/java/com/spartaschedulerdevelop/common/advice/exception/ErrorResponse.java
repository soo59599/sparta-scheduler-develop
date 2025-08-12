package com.spartaschedulerdevelop.common.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorResponse<T> {
    private int status;
    private String code;
    private String message;
    private T data;
    private String path;
    private String timestamp;

    public static ErrorResponse of(HttpStatus status, String code, String message , String path) {
        return new ErrorResponse(status.value(), code, message, null , path , LocalDateTime.now().toString());
    }
}
