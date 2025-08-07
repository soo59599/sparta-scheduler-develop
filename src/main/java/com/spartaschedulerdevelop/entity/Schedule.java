package com.spartaschedulerdevelop.entity;

import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveRequestDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Schedule (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Schedule create(ScheduleSaveRequestDto request) {
        return new Schedule(
                request.getTitle(),
                request.getContent()
        );
    }

    public void updateTitleAndContent(ScheduleUpdateRequestDto request) {
        if(request.getTitle() != null && !request.getTitle().trim().isEmpty()) this.title = request.getTitle();
        if(request.getContent() != null && !request.getContent().trim().isEmpty()) this.content = request.getContent();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
