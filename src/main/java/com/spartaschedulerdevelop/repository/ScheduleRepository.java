package com.spartaschedulerdevelop.repository;

import com.spartaschedulerdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
