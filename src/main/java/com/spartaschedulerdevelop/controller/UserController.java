package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.user.*;
import com.spartaschedulerdevelop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserSaveResponseDto> saveUser(@Valid @RequestBody UserSaveRequestDto request) {
        UserSaveResponseDto response = userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserGetOneResponseDto> getUser (@PathVariable Long userId){
        UserGetOneResponseDto response = userService.getUser(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserGetOneResponseDto>> getUsers(){
        List<UserGetOneResponseDto> responses = userService.getUsers();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserUpdateResponseDto> updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequestDto request) {
        UserUpdateResponseDto response = userService.updateUser(userId, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId, @RequestParam String password){
        userService.deleteUser(userId, password);
        return ResponseEntity.noContent().build();
    }


}
