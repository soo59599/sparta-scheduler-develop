package com.spartaschedulerdevelop.repository;

import com.spartaschedulerdevelop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
