package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.config.PasswordEncoder;
import com.spartaschedulerdevelop.common.advice.ResponseCode;
import com.spartaschedulerdevelop.common.advice.exception.MyCustomException;
import com.spartaschedulerdevelop.common.util.MyCustomUtils;
import com.spartaschedulerdevelop.dto.user.*;
import com.spartaschedulerdevelop.entity.Schedule;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.mapper.UserMapper;
import com.spartaschedulerdevelop.repository.CommentRepository;
import com.spartaschedulerdevelop.repository.ScheduleRepository;
import com.spartaschedulerdevelop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto request) {

        if(userRepository.existsByEmail(request.email())){
            throw new MyCustomException(ResponseCode.DUPLICATE_USER);
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        User savedUser = userRepository.save(User.toUserEntity(request, encodedPassword));

        return userMapper.toUserSaveResponseDto(savedUser);
    }

    @Transactional(readOnly = true)
    public UserGetOneResponseDto getUser(Long userId){

        User user = MyCustomUtils.findByIdOrElseThrow(userRepository, userId, ResponseCode.USER_NOT_FOUND);

        return userMapper.toUserGetOneResponseDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserGetOneResponseDto> getUsers(){

        return userRepository.findAll().stream().map(userMapper::toUserGetOneResponseDto).toList();
    }

    @Transactional
    public UserUpdateResponseDto updateUser(UserUpdateRequestDto request, HttpSession session) {

        Long userId = MyCustomUtils.getCurrentUserId(session);
        User user = MyCustomUtils.findByIdOrElseThrow(userRepository, userId, ResponseCode.USER_NOT_FOUND);

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new MyCustomException(ResponseCode.INVALID_PASSWORD);
        }

        user.update(request);

        return userMapper.toUserUpdateResponseDto(user);
    }

    @Transactional
    public void deleteUser(UserDeleteRequestDto request, HttpSession session) {

        Long userId = MyCustomUtils.getCurrentUserId(session);
        User user = MyCustomUtils.findByIdOrElseThrow(userRepository, userId, ResponseCode.USER_NOT_FOUND);

        if(!passwordEncoder.matches(request.password(), user.getPassword())){
            throw new MyCustomException(ResponseCode.INVALID_PASSWORD);
        }

        //1.유저가 달았던 댓글 삭제
        commentRepository.deleteByUserId(userId);

        //2.유저가 작성한 일정에 달린 댓글 삭제
        List<Schedule> schedules = scheduleRepository.findByUserId(userId);
        schedules.forEach(schedule ->
                commentRepository.deleteByScheduleId(schedule.getId())
        );

        //3.유저가 작성한 일정 삭제
        scheduleRepository.deleteByUserId(userId);

        //4.유저 삭제
        userRepository.deleteById(userId);

        session.invalidate();
    }


}
