package com.spartaschedulerdevelop.mapper;

import com.spartaschedulerdevelop.dto.user.UserGetOneResponseDto;
import com.spartaschedulerdevelop.dto.user.UserSaveResponseDto;
import com.spartaschedulerdevelop.dto.user.UserUpdateResponseDto;
import com.spartaschedulerdevelop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    UserGetOneResponseDto toUserGetOneResponseDto(User user);

    @Mapping(source = "id", target = "userId")
    UserSaveResponseDto toUserSaveResponseDto(User user);

    @Mapping(source = "id", target = "userId")
    UserUpdateResponseDto toUserUpdateResponseDto(User user);
}
