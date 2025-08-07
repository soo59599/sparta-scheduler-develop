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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto save(ScheduleSaveRequestDto request) {

        Schedule savedSchedule =
                scheduleRepository
                .save(Schedule.create(request));

        return ScheduleSaveResponseDto.from(savedSchedule);
    }

    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto findById(Long id) {

        Schedule schedule =
                scheduleRepository
                .findById(id)
                .orElseThrow(()->new MyCustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        return ScheduleGetOneResponseDto.from(schedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetOneResponseDto> findAll() {

        return scheduleRepository
                        .findAllByOrderByCreatedAtDesc()
                        .stream()
                        .map(ScheduleGetOneResponseDto::from)
                        .toList();
    }

}
