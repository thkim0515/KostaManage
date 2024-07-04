package com.example.demo.Comment.repository;

import com.example.demo.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByBoard_PostId(Integer boardId);
}
