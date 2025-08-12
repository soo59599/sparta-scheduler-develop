package com.spartaschedulerdevelop.common.advice.success;

import com.spartaschedulerdevelop.common.advice.exception.ErrorResponse;
import com.spartaschedulerdevelop.common.advice.ResponseCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class SuccessResponseAdvice implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // 실패 응답(ErrorResponse)나 이미 SuccessResponse면 그대로 반환
        if (body instanceof ErrorResponse || body instanceof SuccessResponse) {
            return body;
        }

        // path 가져오기
        String path = request.getURI().getPath();

        // 성공 응답 포맷 적용
        return SuccessResponse.of(
                ResponseCode.SUCCESS.getHttpStatus(),
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMessage(),
                body,
                path                // 요청 경로
        );
    }
}

