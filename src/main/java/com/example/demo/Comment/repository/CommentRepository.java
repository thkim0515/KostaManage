package com.example.demo.Comment.repository;

import com.example.demo.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.board.postId = :boardId AND c.isDeleted = false")
    List<Comment> findByBoard_PostId(Integer boardId);

    @Query("SELECT c FROM Comment c WHERE c.isDeleted = false")
    List<Comment> findAllActive();
}
