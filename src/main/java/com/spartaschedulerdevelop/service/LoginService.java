package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.common.exception.MyCustomException;
import com.spartaschedulerdevelop.dto.login.LoginRequestDto;
import com.spartaschedulerdevelop.dto.login.LoginResponseDto;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.mapper.LoginMapper;
import com.spartaschedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final LoginMapper loginMapper;

    public LoginResponseDto authenticate(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new MyCustomException(ErrorCode.USER_NOT_FOUND));

        if(!ObjectUtils.nullSafeEquals(user.getPassword(), request.password())){
            throw new MyCustomException(ErrorCode.INVALID_CREDENTIALS);
        }


        return loginMapper.toLoginResponseDto(user);
    }
}
