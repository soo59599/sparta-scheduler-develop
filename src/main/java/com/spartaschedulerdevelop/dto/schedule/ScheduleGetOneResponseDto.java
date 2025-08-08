package com.spartaschedulerdevelop.dto.schedule;

import java.time.LocalDateTime;


public record ScheduleGetOneResponseDto(
        Long scheduleId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}
