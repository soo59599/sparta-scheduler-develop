package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.MyCustomException;
import com.spartaschedulerdevelop.common.exception.enums.ErrorCode;
import com.spartaschedulerdevelop.dto.user.*;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSaveResponseDto save(UserSaveRequestDto request) {

        User savedUser = userRepository.save(User.create(request));

        return UserSaveResponseDto.from(savedUser);
    }

    @Transactional(readOnly = true)
    public UserGetOneResponseDto findById(Long id){

        User user = userRepository.findByIdOrElseThrow(id);

        return UserGetOneResponseDto.from(user);
    }

    @Transactional(readOnly = true)
    public List<UserGetOneResponseDto> findAll(){

        return userRepository.findAll().stream().map(UserGetOneResponseDto::from).toList();
    }

    @Transactional
    public UserUpdateResponseDto update(Long id, UserUpdateRequestDto request) {

        User user = userRepository.findByIdOrElseThrow(id);

        if(!user.getPassword().equals(request.getPassword())){
            throw new MyCustomException(ErrorCode.INVALID_PASSWORD);
        }

        user.update(request);
        return UserUpdateResponseDto.from(user);
    }

    public void delete(Long id, String password) {
        User user = userRepository.findByIdOrElseThrow(id);

        if(!user.getPassword().equals(password)){
            throw new MyCustomException(ErrorCode.INVALID_PASSWORD);
        }

        userRepository.deleteById(id);
    }
}
