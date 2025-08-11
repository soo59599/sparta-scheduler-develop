package com.spartaschedulerdevelop.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentSaveRequestDto(

        @NotBlank(message = "일정은 필수 입력입니다.")
        Long scheduleId,

        @NotBlank(message = "내용은 필수 입력입니다.")
        @Size(max = 50, message = "댓글은 50글자 이내여야 합니다.")
        String content
) {
}
