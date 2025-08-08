package com.spartaschedulerdevelop.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSaveRequestDto(

        @NotBlank(message = "이름은 필수입니다.")
        @Size(min = 2, message = "이름은 2글자 이상이어야 합니다.")
        String name,

        @Email(message = "이메일 형식에 맞게 작성해야 합니다.")
        String email,

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String password

) {}
