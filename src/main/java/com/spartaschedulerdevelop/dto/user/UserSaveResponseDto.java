package com.spartaschedulerdevelop.dto.user;

import java.time.LocalDateTime;


public record UserSaveResponseDto(
        Long userId,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}
