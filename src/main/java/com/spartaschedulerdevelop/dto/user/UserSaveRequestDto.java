package com.spartaschedulerdevelop.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSaveRequestDto(

        @NotBlank(message = "이름 입력은 필수입니다.")
        @Size(min = 2, message = "이름은 2글자 이상이어야 합니다.")
        @Size(max = 10, message = "이름은 10글자 이내여야 합니다.")
        String name,

        @Email(message = "이메일 형식에 맞게 작성해야 합니다.")
        @NotBlank(message = "이메일 입력은 필수입니다.")
        String email,

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        @Pattern(regexp = "^(?=.*[a-z A-Z])(?=.*\\d).{2,}$", message = "비밀번호는 2글자 이상이어야 하며, 최소 한 개 이상의 영문자와 숫자를 포함해야 합니다.")
        String password

) {}
