package com.spartaschedulerdevelop.mapper;

import com.spartaschedulerdevelop.dto.user.UserGetOneResponseDto;
import com.spartaschedulerdevelop.dto.user.UserSaveResponseDto;
import com.spartaschedulerdevelop.dto.user.UserUpdateResponseDto;
import com.spartaschedulerdevelop.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserGetOneResponseDto toUserGetOneResponseDto(User user);

    UserSaveResponseDto toUserSaveResponseDto(User user);

    UserUpdateResponseDto toUserUpdateResponseDto(User user);
}
