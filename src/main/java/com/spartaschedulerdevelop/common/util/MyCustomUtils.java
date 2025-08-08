package com.spartaschedulerdevelop.common.util;

import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.common.exception.MyCustomException;
import org.springframework.data.jpa.repository.JpaRepository;

public class MyCustomUtils {

    public static <T, ID> T findByIdOrElseThrow( JpaRepository<T, ID> repository, ID id, ErrorCode errorCode ){
        return repository.findById(id).orElseThrow(() -> new MyCustomException(errorCode));
    }

}