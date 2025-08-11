package com.spartaschedulerdevelop.service;

import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.common.exception.MyCustomException;
import com.spartaschedulerdevelop.common.util.MyCustomUtils;
import com.spartaschedulerdevelop.dto.comment.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentSaveResponseDto saveComment(CommentSaveRequestDto request, Long scheduleId, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        User foundUser = MyCustomUtils.findByIdOrElseThrow(userRepository, currentUserId, ErrorCode.USER_NOT_FOUND);

        Schedule foundSchedule = MyCustomUtils.findByIdOrElseThrow(scheduleRepository, scheduleId, ErrorCode.SCHEDULE_NOT_FOUND);

        Comment comment = Comment.toCommentEntity(request, foundUser, foundSchedule);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toCommentSaveResponseDto(savedComment);
    }

    @Transactional(readOnly = true)
    public List<CommentGetOneResponseDto> getComments(Long scheduleId) {

        return commentRepository
                .findByScheduleIdOrderByCreatedAtDesc(scheduleId)
                .stream()
                .map(commentMapper::toCommentGetOneResponseDto)
                .toList();
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(CommentUpdateRequestDto request, Long commentId, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        Comment foundComment = MyCustomUtils.findByIdOrElseThrow(commentRepository, commentId, ErrorCode.COMMENT_NOT_FOUND);

        if(!ObjectUtils.nullSafeEquals(foundComment.getUser().getId(), currentUserId)){
            throw new MyCustomException(ErrorCode.FORBIDDEN_UPDATE);
        }

        foundComment.updateContent(request);

        return commentMapper.toCommentUpdateResponseDto(foundComment);
    }

    @Transactional
    public void deleteComment(Long commentId, HttpSession session) {

        Long currentUserId = MyCustomUtils.getCurrentUserId(session);
        Comment foundComment = MyCustomUtils.findByIdOrElseThrow(commentRepository, commentId, ErrorCode.COMMENT_NOT_FOUND);

        if(!ObjectUtils.nullSafeEquals(foundComment.getUser().getId(), currentUserId)){
            throw new MyCustomException(ErrorCode.FORBIDDEN_DELETE);
        }

        commentRepository.delete(foundComment);

    }
}
