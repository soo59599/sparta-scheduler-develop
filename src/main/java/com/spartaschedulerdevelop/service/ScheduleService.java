package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.dto.schedule.*;
import com.spartaschedulerdevelop.entity.Schedule;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.repository.ScheduleRepository;
import com.spartaschedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleSaveResponseDto save(ScheduleSaveRequestDto request) {

        User user = userRepository.findByNameOrElseThrow(request.getName());

        Schedule schedule = Schedule.create(request);

        schedule.setUser(user);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleSaveResponseDto.from(savedSchedule);
    }

    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto findById(Long id) {

        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

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

    @Transactional
    public ScheduleUpdateResponseDto update(Long id, ScheduleUpdateRequestDto request) {

        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        schedule.updateTitleAndContent(request);

        return ScheduleUpdateResponseDto.from(schedule);
    }

    @Transactional
    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        scheduleRepository.delete(schedule);

    }
}
