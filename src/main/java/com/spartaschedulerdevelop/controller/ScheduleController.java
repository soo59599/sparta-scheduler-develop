package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveRequestDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleSaveResponseDto;
import com.spartaschedulerdevelop.dto.schedule.ScheduleGetOneResponseDto;
import com.spartaschedulerdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleSaveResponseDto> save(@Valid @RequestBody ScheduleSaveRequestDto request) {
        ScheduleSaveResponseDto response = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleGetOneResponseDto> findById(@PathVariable Long id){
        ScheduleGetOneResponseDto response = scheduleService.findById(id);
        return ResponseEntity.ok(response);
    }
}
