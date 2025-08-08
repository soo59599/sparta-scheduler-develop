package com.spartaschedulerdevelop.mapper;

import com.spartaschedulerdevelop.dto.login.LoginResponseDto;
import com.spartaschedulerdevelop.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {

    LoginResponseDto toLoginResponseDto (User user);

}
