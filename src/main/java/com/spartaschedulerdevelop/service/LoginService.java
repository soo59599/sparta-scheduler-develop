package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.common.exception.MyCustomException;
import com.spartaschedulerdevelop.dto.login.LoginRequestDto;
import com.spartaschedulerdevelop.dto.login.LoginResponseDto;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public LoginResponseDto authenticate(LoginRequestDto request, HttpSession session) {
        User user = userRepository.findByEmailOrElseThrow(request.getEmail());
        if(!user.getPassword().equals(request.getPassword())){
            throw new MyCustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        session.setAttribute("userId", user.getId());

        return LoginResponseDto.toDto(user);
    }
}
