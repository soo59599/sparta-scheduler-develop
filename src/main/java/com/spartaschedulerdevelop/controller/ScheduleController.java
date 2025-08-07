package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.schedule.*;
import com.spartaschedulerdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ScheduleGetAllResponseDto>> findAll(){
        List<ScheduleGetAllResponseDto> responses = scheduleService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleUpdateResponseDto> update(@PathVariable Long id, @Valid @RequestBody ScheduleUpdateRequestDto request) {
        ScheduleUpdateResponseDto response = scheduleService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        scheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
