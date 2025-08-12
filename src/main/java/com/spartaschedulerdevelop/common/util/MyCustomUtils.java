package com.spartaschedulerdevelop.common.util;

import com.spartaschedulerdevelop.common.advice.ResponseCode;
import com.spartaschedulerdevelop.common.advice.exception.MyCustomException;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.jpa.repository.JpaRepository;

public class MyCustomUtils {

    // 레파지토리에서 Id기준으로 가져오기
    public static <T, ID> T findByIdOrElseThrow( JpaRepository<T, ID> repository, ID id, ResponseCode responseCode){
        return repository.findById(id).orElseThrow(() -> new MyCustomException(responseCode));
    }

    // 세션에서 현재 사용자 ID 가져오기
    public static Long getCurrentUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new MyCustomException(ResponseCode.UNAUTHORIZED);
        }
        return userId;
    }

}