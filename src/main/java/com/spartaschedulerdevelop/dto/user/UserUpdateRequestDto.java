package com.spartaschedulerdevelop.dto.user;

import com.spartaschedulerdevelop.common.validation.AtLeastOneNotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@AtLeastOneNotNull(fields = {"email", "name"}, message = "수정할 항목 중 하나는 필수 입력입니다.")
public record UserUpdateRequestDto(

        @Email(message = "이메일 형식에 맞게 작성해야 합니다.")
        String email,

        @Size(min = 2, message = "이름은 2글자 이상이어야 합니다.")
        @Size(max = 10, message = "이름은 10글자 이내여야 합니다.")
        String name,

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String password

) {}