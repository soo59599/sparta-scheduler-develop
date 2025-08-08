package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.common.util.MyCustomUtils;
import com.spartaschedulerdevelop.dto.schedule.*;
import com.spartaschedulerdevelop.entity.Schedule;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.mapper.ScheduleMapper;
import com.spartaschedulerdevelop.repository.ScheduleRepository;
import com.spartaschedulerdevelop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ScheduleMapper scheduleMapper;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto request, HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");
        User user = MyCustomUtils.findByIdOrElseThrow(userRepository, userId, ErrorCode.USER_NOT_FOUND);

        Schedule schedule = Schedule.toSchedule(request);
        schedule.setUser(user);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleSaveResponseDto(savedSchedule);
    }

    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto getSchedule(Long scheduleId) {

        Schedule schedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ErrorCode.SCHEDULE_NOT_FOUND);

        return scheduleMapper.toScheduleGetOneResponseDto(schedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleGetOneResponseDto> getSchedules() {

        return scheduleRepository
                        .findAllByOrderByCreatedAtDesc()
                        .stream()
                        .map(scheduleMapper::toScheduleGetOneResponseDto)
                        .toList();
    }

    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto request) {

        Schedule schedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ErrorCode.SCHEDULE_NOT_FOUND);

        schedule.updateTitleAndContent(request);

        return scheduleMapper.toScheduleUpdateResponseDto(schedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        Schedule schedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ErrorCode.SCHEDULE_NOT_FOUND);

        scheduleRepository.delete(schedule);

    }
}
