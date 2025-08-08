package com.spartaschedulerdevelop.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @Email(message = "이메일 형식에 맞게 작성해야 합니다.")
        String email,

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String password
) {}