package com.spartaschedulerdevelop.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    @Size(min = 2, message = "이름은 2글자 이상이어야 합니다.")
    private String name;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 30, message = "제목은 30글자 이내여야 합니다.")
    private String title;

    @NotBlank
    @Size(max= 300, message = "내용은 300글자 이내여야 합니다.")
    private String content;

}
