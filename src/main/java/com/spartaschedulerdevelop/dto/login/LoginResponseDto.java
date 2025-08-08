package com.spartaschedulerdevelop.dto.login;

import com.spartaschedulerdevelop.entity.User;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private Long id;

    private String name;

    private String email;

    private LoginResponseDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static LoginResponseDto toDto(User user) {
        return new LoginResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail());
    }
}
