package com.spartaschedulerdevelop.dto.schedule;

import java.time.LocalDateTime;

public record ScheduleUpdateResponseDto(
        Long scheduleId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt)
{}
