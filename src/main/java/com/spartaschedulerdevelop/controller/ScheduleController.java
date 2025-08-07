package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.ScheduleSaveRequestDto;
import com.spartaschedulerdevelop.dto.ScheduleSaveResponseDto;
import com.spartaschedulerdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(@Valid @RequestBody ScheduleSaveRequestDto request) {
        ScheduleSaveResponseDto response = scheduleService.save(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
