package com.spartaschedulerdevelop.dto.user;

import com.spartaschedulerdevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserUpdateResponseDto {

    private final Long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private UserUpdateResponseDto(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserUpdateResponseDto from(User user) {
        return new UserUpdateResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
