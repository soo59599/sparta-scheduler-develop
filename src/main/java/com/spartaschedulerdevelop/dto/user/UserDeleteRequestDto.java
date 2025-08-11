package com.spartaschedulerdevelop.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UserDeleteRequestDto (

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String password
){}
