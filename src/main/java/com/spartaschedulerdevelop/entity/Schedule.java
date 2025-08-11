package com.spartaschedulerdevelop.entity;

import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveRequestDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Schedule (String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public static Schedule toScheduleEntity(ScheduleSaveRequestDto request, User user) {
        return new Schedule(
                request.title(),
                request.content(),
                user
        );
    }

    public void updateTitleAndContent(ScheduleUpdateRequestDto request) {
        if(StringUtils.hasText(request.title())) this.title = request.title();
        if(StringUtils.hasText(request.content())) this.content = request.content();
    }

}
