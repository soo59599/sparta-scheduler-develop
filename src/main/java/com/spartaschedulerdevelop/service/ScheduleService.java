package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.advice.ResponseCode;
import com.spartaschedulerdevelop.common.advice.exception.MyCustomException;
import com.spartaschedulerdevelop.common.util.MyCustomUtils;
import com.spartaschedulerdevelop.dto.schedule.*;
import com.spartaschedulerdevelop.entity.Schedule;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.mapper.ScheduleMapper;
import com.spartaschedulerdevelop.repository.CommentRepository;
import com.spartaschedulerdevelop.repository.ScheduleRepository;
import com.spartaschedulerdevelop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final CommentRepository commentRepository;

    //일정 저장
    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto request, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        User foundUser = MyCustomUtils.findByIdOrElseThrow(userRepository, currentUserId, ResponseCode.USER_NOT_FOUND);

        Schedule schedule = Schedule.toScheduleEntity(request, foundUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toScheduleSaveResponseDto(savedSchedule);
    }

    //일정 단건 조회
    @Transactional(readOnly = true)
    public ScheduleGetOneResponseDto getSchedule(Long scheduleId) {

        Schedule foundSchedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ResponseCode.SCHEDULE_NOT_FOUND);

        return scheduleMapper.toScheduleGetOneResponseDto(foundSchedule);
    }

    //일정 전체 조회(페이징x)
    @Transactional(readOnly = true)
    public List<ScheduleGetOneResponseDto> getSchedules() {

        return scheduleRepository
                        .findAllByOrderByModifiedAtDesc()
                        .stream()
                        .map(scheduleMapper::toScheduleGetOneResponseDto)
                        .toList();
    }

    //일정 전체 조회(페이징o)
    @Transactional(readOnly = true)
    public Page<SchedulePageResponseDto> getSchedulePage(Pageable pageable) {
        return scheduleRepository.findSchedulePageResponseDto(pageable);
    }

    //일정 수정
    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto request, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        Schedule foundSchedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ResponseCode.SCHEDULE_NOT_FOUND);

        if(!ObjectUtils.nullSafeEquals(foundSchedule.getUser().getId(), currentUserId)){
            throw new MyCustomException(ResponseCode.FORBIDDEN_UPDATE);
        }

        foundSchedule.updateTitleAndContent(request);

        return scheduleMapper.toScheduleUpdateResponseDto(foundSchedule);
    }

    //일정 삭제
    @Transactional
    public void deleteSchedule(Long scheduleId, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        Schedule foundSchedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ResponseCode.SCHEDULE_NOT_FOUND);

        if(!ObjectUtils.nullSafeEquals(foundSchedule.getUser().getId(), currentUserId)){
            throw new MyCustomException(ResponseCode.FORBIDDEN_DELETE);
        }

        commentRepository.deleteByScheduleId(scheduleId);

        scheduleRepository.delete(foundSchedule);

    }


}
