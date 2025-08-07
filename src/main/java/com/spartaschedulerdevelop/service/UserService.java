package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.dto.user.UserGetOneResponseDto;
import com.spartaschedulerdevelop.dto.user.UserSaveRequestDto;
import com.spartaschedulerdevelop.dto.user.UserSaveResponseDto;
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
}
