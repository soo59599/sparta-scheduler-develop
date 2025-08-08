package com.spartaschedulerdevelop.dto.user;

import java.time.LocalDateTime;


public record UserSaveResponseDto(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}
