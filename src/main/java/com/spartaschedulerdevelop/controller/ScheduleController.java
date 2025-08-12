package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.schedule.*;
import com.spartaschedulerdevelop.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(@Valid @RequestBody ScheduleSaveRequestDto request, HttpSession session) {
        
        ScheduleSaveResponseDto response = scheduleService.saveSchedule(request, session);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleGetOneResponseDto> getSchedule(@PathVariable Long scheduleId){
        ScheduleGetOneResponseDto response = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleGetOneResponseDto>> getSchedules(){
        List<ScheduleGetOneResponseDto> responses = scheduleService.getSchedules();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SchedulePageResponseDto>> getSchedulePage(@PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<SchedulePageResponseDto> response = scheduleService.getSchedulePage(pageable);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponseDto> updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody ScheduleUpdateRequestDto request, HttpSession session) {
        ScheduleUpdateResponseDto response = scheduleService.updateSchedule(scheduleId, request, session);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, HttpSession session){
        scheduleService.deleteSchedule(scheduleId, session);
        return ResponseEntity.noContent().build();
    }
}
