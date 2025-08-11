package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.common.exception.MyCustomException;
import com.spartaschedulerdevelop.common.util.MyCustomUtils;
import com.spartaschedulerdevelop.dto.user.*;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.mapper.UserMapper;
import com.spartaschedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto request) {

        userRepository.findByEmail(request.email())
                .ifPresent(user -> { throw new MyCustomException(ErrorCode.DUPLICATE_USER); });

        User savedUser = userRepository.save(User.toUserEntity(request));

        return userMapper.toUserSaveResponseDto(savedUser);
    }

    @Transactional(readOnly = true)
    public UserGetOneResponseDto getUser(Long userId){

        User user = MyCustomUtils.findByIdOrElseThrow(userRepository, userId, ErrorCode.USER_NOT_FOUND);

        return userMapper.toUserGetOneResponseDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserGetOneResponseDto> getUsers(){

        return userRepository.findAll().stream().map(userMapper::toUserGetOneResponseDto).toList();
    }

    @Transactional
    public UserUpdateResponseDto updateUser(Long userId, UserUpdateRequestDto request) {

        User user = MyCustomUtils.findByIdOrElseThrow(userRepository, userId, ErrorCode.USER_NOT_FOUND);

        if(!user.getPassword().equals(request.password())){
            throw new MyCustomException(ErrorCode.INVALID_PASSWORD);
        }

        user.update(request);
        return userMapper.toUserUpdateResponseDto(user);
    }

    public void deleteUser(Long userId, String password) {
        User user = MyCustomUtils.findByIdOrElseThrow(userRepository, userId, ErrorCode.USER_NOT_FOUND);

        if(!ObjectUtils.nullSafeEquals(password, user.getPassword())){
            throw new MyCustomException(ErrorCode.INVALID_PASSWORD);
        }

        userRepository.deleteById(userId);
    }


}
