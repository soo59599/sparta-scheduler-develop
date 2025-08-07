package com.spartaschedulerdevelop.dto.user;

import com.spartaschedulerdevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserGetOneResponseDto {

    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private UserGetOneResponseDto(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static UserGetOneResponseDto from(User user) {
        return new UserGetOneResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
