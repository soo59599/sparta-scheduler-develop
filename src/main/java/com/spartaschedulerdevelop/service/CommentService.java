package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.common.util.MyCustomUtils;
import com.spartaschedulerdevelop.dto.comment.CommentSaveRequestDto;
import com.spartaschedulerdevelop.dto.comment.CommentSaveResponseDto;
import com.spartaschedulerdevelop.entity.Comment;
import com.spartaschedulerdevelop.entity.Schedule;
import com.spartaschedulerdevelop.entity.User;
import com.spartaschedulerdevelop.mapper.CommentMapper;
import com.spartaschedulerdevelop.repository.CommentRepository;
import com.spartaschedulerdevelop.repository.ScheduleRepository;
import com.spartaschedulerdevelop.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    public CommentSaveResponseDto saveComment(CommentSaveRequestDto request, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        User foundUser = MyCustomUtils.findByIdOrElseThrow(userRepository, currentUserId, ErrorCode.USER_NOT_FOUND);

        Schedule foundSchedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, request.scheduleId(), ErrorCode.SCHEDULE_NOT_FOUND);

        Comment comment = Comment.toCommentEntity(request, foundUser, foundSchedule);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toCommentSaveResponseDto(savedComment);
    }
}
