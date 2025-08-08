package com.spartaschedulerdevelop.mapper;

import com.spartaschedulerdevelop.dto.schedule.ScheduleGetOneResponseDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveResponseDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleUpdateResponseDto;
import com.spartaschedulerdevelop.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleGetOneResponseDto toScheduleGetOneResponseDto (Schedule schedule);

    ScheduleSaveResponseDto toScheduleSaveResponseDto (Schedule schedule);

    ScheduleUpdateResponseDto toScheduleUpdateResponseDto (Schedule schedule);

}
