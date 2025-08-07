package com.spartaschedulerdevelop.repository;

import com.spartaschedulerdevelop.common.exception.MyCustomException;
import com.spartaschedulerdevelop.common.exception.enums.ErrorCode;
import com.spartaschedulerdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    default User findByNameOrElseThrow(String name){
        return findByName(name).orElseThrow(() -> new MyCustomException(ErrorCode.USER_NOT_FOUND));
    }

    default User findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new MyCustomException(ErrorCode.USER_NOT_FOUND));
    }

}
