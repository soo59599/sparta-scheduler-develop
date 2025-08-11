package com.spartaschedulerdevelop.mapper;

import com.spartaschedulerdevelop.dto.comment.CommentSaveResponseDto;
import com.spartaschedulerdevelop.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "id", target = "commentId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "schedule.id", target = "scheduleId")
    CommentSaveResponseDto toCommentSaveResponseDto(Comment comment);

}
