package com.spartaschedulerdevelop.dto.user;

import java.time.LocalDateTime;

public record UserGetOneResponseDto(
        Long id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}
