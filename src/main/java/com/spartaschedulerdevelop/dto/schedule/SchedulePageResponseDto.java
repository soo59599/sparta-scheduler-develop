package com.spartaschedulerdevelop.dto.schedule;

import java.time.LocalDateTime;

//페이징 전체 일정 조회 Dto
public record SchedulePageResponseDto(
        Long id,
        String title,
        String content,
        Long commentCount,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String name
) {}
