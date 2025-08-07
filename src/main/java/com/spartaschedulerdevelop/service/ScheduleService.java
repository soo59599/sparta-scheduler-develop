package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.MyCustomException;
import com.spartaschedulerdevelop.common.exception.enums.ErrorCode;
import com.spartaschedulerdevelop.dto.schedule.ScheduleGetOneResponseDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveRequestDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveResponseDto;
import com.spartaschedulerdevelop.entity.Schedule;
import com.spartaschedulerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleSaveResponseDto save(ScheduleSaveRequestDto request) {

        Schedule savedSchedule =
                scheduleRepository
                .save(Schedule.create(request));

        return ScheduleSaveResponseDto.from(savedSchedule);
    }

    public ScheduleGetOneResponseDto findById(Long id) {

        Schedule schedule =
                scheduleRepository
                .findById(id)
                .orElseThrow(()->new MyCustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        return ScheduleGetOneResponseDto.from(schedule);
    }
}
