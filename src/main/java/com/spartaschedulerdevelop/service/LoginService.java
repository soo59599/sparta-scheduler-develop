package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.config.PasswordEncoder;
import com.spartaschedulerdevelop.common.advice.ResponseCode;
import com.spartaschedulerdevelop.common.advice.exception.MyCustomException;
import com.spartaschedulerdevelop.dto.login.LoginRequestDto;
import com.spartaschedulerdevelop.dto.login.LoginResponseDto;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.mapper.LoginMapper;
import com.spartaschedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final LoginMapper loginMapper;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto authenticate(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new MyCustomException(ResponseCode.INVALID_CREDENTIALS));

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new MyCustomException(ResponseCode.INVALID_CREDENTIALS);
        }

        return loginMapper.toLoginResponseDto(user);
    }
}
