package com.spartaschedulerdevelop.repository;

import com.spartaschedulerdevelop.dto.schedule.SchedulePageResponseDto;
import com.spartaschedulerdevelop.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByOrderByCreatedAtDesc();

    void deleteByUserId(Long userId);

    List<Schedule> findByUserId(Long userId);

    @Query("SELECT new com.spartaschedulerdevelop.dto.schedule.SchedulePageResponseDto(" +
            "s.id, " +
            "s.title, " +
            "s.content, " +
            "(SELECT COUNT(c) FROM Comment c WHERE c.schedule = s), " +
            "s.createdAt, " +
            "s.modifiedAt, " +
            "u.name )" +
            "FROM Schedule s " +
            "JOIN s.user u ")
    Page<SchedulePageResponseDto> findSchedulePageResponseDto(Pageable pageable);
}
