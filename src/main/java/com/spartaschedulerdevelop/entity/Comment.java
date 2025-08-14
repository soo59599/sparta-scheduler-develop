package com.spartaschedulerdevelop.entity;

import com.spartaschedulerdevelop.dto.comment.CommentSaveRequestDto;
import com.spartaschedulerdevelop.dto.comment.CommentUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    private Comment(String content, User user, Schedule schedule) {
        this.content = content;
        this.user = user;
        this.schedule = schedule;
    }

    public static Comment toCommentEntity(CommentSaveRequestDto request, User user, Schedule schedule) {
        return new Comment(
                request.content(),
                user,
                schedule
        );
    }

    public void updateContent(CommentUpdateRequestDto request) {
        this.content = request.content();
    }
}
