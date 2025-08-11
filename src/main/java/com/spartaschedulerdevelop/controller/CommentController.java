package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.comment.CommentSaveRequestDto;
import com.spartaschedulerdevelop.dto.comment.CommentSaveResponseDto;
import com.spartaschedulerdevelop.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentSaveResponseDto> saveComment(@RequestBody CommentSaveRequestDto request, HttpSession session) {
        CommentSaveResponseDto response = commentService.saveComment(request, session);
        return ResponseEntity.ok(response);
    }

}
