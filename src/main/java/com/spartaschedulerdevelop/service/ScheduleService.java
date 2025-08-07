package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.dto.ScheduleSaveRequestDto;
import com.spartaschedulerdevelop.dto.ScheduleSaveResponseDto;
import com.spartaschedulerdevelop.entity.Schedule;
import com.spartaschedulerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleSaveResponseDto save(ScheduleSaveRequestDto request) {

        Schedule savedSchedule = scheduleRepository.save(Schedule.create(request));

        return ScheduleSaveResponseDto.from(savedSchedule);
    }
}
