package com.spartaschedulerdevelop.dto.schedule;

import com.spartaschedulerdevelop.common.validation.AtLeastOneNotNull;

@AtLeastOneNotNull(fields = {"title", "content"}, message = "제목 또는 내용 중 하나는 필수입니다.")
public record ScheduleUpdateRequestDto (
        String title,
        String content
) {}
