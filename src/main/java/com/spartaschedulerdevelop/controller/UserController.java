package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.user.UserSaveRequestDto;
import com.spartaschedulerdevelop.dto.user.UserSaveResponseDto;
import com.spartaschedulerdevelop.dto.user.UserGetOneResponseDto;
import com.spartaschedulerdevelop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserSaveResponseDto> save(@Valid @RequestBody UserSaveRequestDto request) {
        UserSaveResponseDto response = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetOneResponseDto> findById (@PathVariable Long id){
        UserGetOneResponseDto response = userService.findById(id);
        return ResponseEntity.ok(response);
    }


}
