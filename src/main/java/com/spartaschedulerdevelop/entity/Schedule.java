package com.spartaschedulerdevelop.entity;

import com.spartaschedulerdevelop.dto.ScheduleSaveRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String title;

    private String content;

    private Schedule (String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public static Schedule create(ScheduleSaveRequest request) {
        return new Schedule(
                request.getAuthor(),
                request.getTitle(),
                request.getContent()
        );
    }
}
