package com.spartaschedulerdevelop.dto.schedule;

import java.time.LocalDateTime;

public record ScheduleSaveResponseDto (
        Long scheduleId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}
