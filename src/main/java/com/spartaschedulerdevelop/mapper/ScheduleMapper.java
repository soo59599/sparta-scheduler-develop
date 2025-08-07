package com.spartaschedulerdevelop.mapper;

import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveRequestDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveResponseDto;
import com.spartaschedulerdevelop.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    // Schedule → ScheduleSaveResponseDto
    ScheduleSaveResponseDto from(Schedule schedule);

    // ScheduleSaveRequestDto → Schedule (필요한 경우)
    Schedule toEntity(ScheduleSaveRequestDto requestDto);
}