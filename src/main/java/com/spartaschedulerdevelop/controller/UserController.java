package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.user.*;
import com.spartaschedulerdevelop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UserGetOneResponseDto>> findAll(){
        List<UserGetOneResponseDto> responses = userService.findAll();
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserUpdateResponseDto> update(@PathVariable Long id, @Valid @RequestBody UserUpdateRequestDto request) {
        UserUpdateResponseDto response = userService.update(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam String password){
        userService.delete(id, password);
        return ResponseEntity.noContent().build();
    }


}
