package com.spartaschedulerdevelop.repository;

import com.spartaschedulerdevelop.common.exception.MyCustomException;
import com.spartaschedulerdevelop.common.exception.ErrorCode;
import com.spartaschedulerdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByOrderByCreatedAtDesc();

    default Schedule findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new MyCustomException(ErrorCode.SCHEDULE_NOT_FOUND));
    }

}
