package com.spartaschedulerdevelop.common.advice.success;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SuccessResponse<T>{
    private int status;
    private String code;
    private String message;
    private T data;
    private String path;
    private String timestamp;

    public static <T> SuccessResponse<T> of(HttpStatus status, String code, String message, T data , String path) {
        return new SuccessResponse(status.value(), code, message, data , path, LocalDateTime.now().toString());
    }
}
