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

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Schedule (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Schedule toScheduleEntity(ScheduleSaveRequestDto request) {
        return new Schedule(
                request.title(),
                request.content()
        );
    }

    public void updateTitleAndContent(ScheduleUpdateRequestDto request) {
        if(StringUtils.hasText(request.title())) this.title = request.title();
        if(StringUtils.hasText(request.content())) this.content = request.content();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
