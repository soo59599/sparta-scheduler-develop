package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.common.exception.MyCustomException;
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
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final ScheduleMapper scheduleMapper;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto request, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        User foundUser = MyCustomUtils.findByIdOrElseThrow(userRepository, currentUserId, ErrorCode.USER_NOT_FOUND);

        Schedule schedule = Schedule.toScheduleEntity(request, foundUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleSaveResponseDto(savedSchedule);
    }

    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto getSchedule(Long scheduleId) {

        Schedule foundSchedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ErrorCode.SCHEDULE_NOT_FOUND);

        return scheduleMapper.toScheduleGetOneResponseDto(foundSchedule);
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
    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto request, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        Schedule foundSchedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ErrorCode.SCHEDULE_NOT_FOUND);

        if(!ObjectUtils.nullSafeEquals(foundSchedule.getUser().getId(), currentUserId)){
            throw new MyCustomException(ErrorCode.FORBIDDEN_UPDATE);
        }

        foundSchedule.updateTitleAndContent(request);

        return scheduleMapper.toScheduleUpdateResponseDto(foundSchedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        Schedule foundSchedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ErrorCode.SCHEDULE_NOT_FOUND);

        if(!ObjectUtils.nullSafeEquals(foundSchedule.getUser().getId(), currentUserId)){
            throw new MyCustomException(ErrorCode.FORBIDDEN_DELETE);
        }

        scheduleRepository.delete(foundSchedule);

    }
}
