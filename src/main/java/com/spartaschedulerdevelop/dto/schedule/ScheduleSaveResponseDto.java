package com.spartaschedulerdevelop.dto.schedule;

import com.spartaschedulerdevelop.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponseDto {

    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private ScheduleSaveResponseDto(Long id, String title, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ScheduleSaveResponseDto from(Schedule schedule) {
        return new ScheduleSaveResponseDto(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

}
