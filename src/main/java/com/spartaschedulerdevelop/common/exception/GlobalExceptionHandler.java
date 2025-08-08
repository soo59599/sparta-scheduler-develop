package com.spartaschedulerdevelop.common.exception;

import com.spartaschedulerdevelop.common.exception.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        ErrorResponse error =
                ErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                "VAL-001",
                "입력값 오류: " + msg,
                request.getRequestURI()
        );

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<ErrorResponse> handleCustom(MyCustomException ex, HttpServletRequest request) {
        ErrorResponse error =
                ErrorResponse.of(
                HttpStatus.BAD_REQUEST,
                        ex.getCode(),
                        ex.getMessage(),
                        request.getRequestURI()
                );
        return ResponseEntity.badRequest().body(error);
    }

}
