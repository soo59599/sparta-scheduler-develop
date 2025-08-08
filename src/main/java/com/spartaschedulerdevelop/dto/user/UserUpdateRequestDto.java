package com.spartaschedulerdevelop.dto.user;

import com.spartaschedulerdevelop.common.validation.AtLeastOneNotNull;
import jakarta.validation.constraints.NotBlank;

@AtLeastOneNotNull(fields = {"email", "name"}, message = "수정할 항목 중 하나는 필수 입력입니다.")
public record UserUpdateRequestDto(

        String email,

        String name,

        @NotBlank(message = "비밀번호 입력은 필수입니다.")
        String password

) {}