package com.spartaschedulerdevelop.dto.schedule;

import java.time.LocalDateTime;

//단순 일정 조회 Dto
public record ScheduleGetOneResponseDto(

        Long scheduleId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}
