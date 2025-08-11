package com.spartaschedulerdevelop.controller;

import com.spartaschedulerdevelop.dto.comment.*;
import com.spartaschedulerdevelop.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedules/{scheduleId}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentSaveResponseDto> saveComment(@RequestBody CommentSaveRequestDto request, @PathVariable Long scheduleId, HttpSession session) {
        CommentSaveResponseDto response = commentService.saveComment(request,scheduleId, session);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CommentGetOneResponseDto>> getComment(@PathVariable Long scheduleId) {
        List<CommentGetOneResponseDto> responses = commentService.getComments(scheduleId);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentUpdateResponseDto> updateComment(@RequestBody CommentUpdateRequestDto request, @PathVariable Long commentId, HttpSession session) {
        CommentUpdateResponseDto response = commentService.updateComment(request, commentId, session);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, HttpSession session){
        commentService.deleteComment(commentId, session);
        return ResponseEntity.noContent().build();
    }

}
