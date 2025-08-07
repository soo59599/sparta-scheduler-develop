package com.spartaschedulerdevelop.dto.user;

import com.spartaschedulerdevelop.common.validation.AtLeastOneNotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@AtLeastOneNotNull(fields = {"email", "name"}, message = "수정할 항목 중 하나는 필수 입력입니다.")
public class UserUpdateRequestDto {

    private String email;

    private String name;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    private String password;

}
