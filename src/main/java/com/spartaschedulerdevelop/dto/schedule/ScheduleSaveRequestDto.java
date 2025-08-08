package com.spartaschedulerdevelop.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ScheduleSaveRequestDto(

        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 30, message = "제목은 30글자 이내여야 합니다.")
        String title,

        @NotBlank
        @Size(max = 300, message = "내용은 300글자 이내여야 합니다.")
        String content

) {}
