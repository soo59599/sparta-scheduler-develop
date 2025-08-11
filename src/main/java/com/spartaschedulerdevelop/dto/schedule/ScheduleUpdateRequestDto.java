package com.spartaschedulerdevelop.dto.schedule;

import com.spartaschedulerdevelop.common.validation.AtLeastOneNotNull;
import jakarta.validation.constraints.Size;

@AtLeastOneNotNull(fields = {"title", "content"}, message = "제목 또는 내용 중 하나는 필수 입력입니다.")
public record ScheduleUpdateRequestDto (

        @Size(max = 30, message = "제목은 30글자 이내여야 합니다.")
        String title,

        @Size(max = 300, message = "내용은 300글자 이내여야 합니다.")
        String content
) {}
