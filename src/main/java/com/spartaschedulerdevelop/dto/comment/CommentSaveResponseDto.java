package com.spartaschedulerdevelop.dto.comment;

import java.time.LocalDateTime;

public record CommentSaveResponseDto(
        Long commentId,
        Long scheduleId,
        Long userId,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}
