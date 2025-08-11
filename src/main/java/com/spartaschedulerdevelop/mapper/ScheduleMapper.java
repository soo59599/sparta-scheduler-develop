package com.spartaschedulerdevelop.mapper;

import com.spartaschedulerdevelop.dto.schedule.ScheduleGetOneResponseDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveResponseDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleUpdateResponseDto;
import com.spartaschedulerdevelop.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(source = "id", target = "scheduleId")
    ScheduleGetOneResponseDto toScheduleGetOneResponseDto (Schedule schedule);

    @Mapping(source = "id", target = "scheduleId")
    ScheduleSaveResponseDto toScheduleSaveResponseDto (Schedule schedule);

    @Mapping(source = "id", target = "scheduleId")
    ScheduleUpdateResponseDto toScheduleUpdateResponseDto (Schedule schedule);

}
