package com.spartaschedulerdevelop.dto.schedule;

import com.spartaschedulerdevelop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleGetOneResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private ScheduleGetOneResponseDto(Long id, String title, String content, String author, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ScheduleGetOneResponseDto from(Schedule schedule) {
        return new ScheduleGetOneResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
