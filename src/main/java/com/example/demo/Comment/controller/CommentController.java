package com.example.demo.Comment.controller;

import com.example.demo.Comment.entity.Comment;
import com.example.demo.Comment.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 생성
    @PostMapping("/create")
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(
                comment.getBoard().getPostId(),
                comment.getUser().getUserId(),
                comment.getContent(),
                comment.getParent() != null ? comment.getParent().getCommentId() : null
        );
    }

    // 특정 댓글 조회
    @GetMapping("/get/{id}")
    public Optional<Comment> getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    // 모든 댓글 조회
    @GetMapping("/all")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    // 댓글 수정
    @PutMapping("/update/{id}")
    public Comment updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        return commentService.updateComment(id, comment.getContent());
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }
}
