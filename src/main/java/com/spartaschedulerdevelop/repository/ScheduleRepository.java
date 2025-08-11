package com.spartaschedulerdevelop.repository;

import com.spartaschedulerdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByOrderByCreatedAtDesc();

    void deleteByUserId(Long userId);

    List<Schedule> findByUserId(Long userId);
}
