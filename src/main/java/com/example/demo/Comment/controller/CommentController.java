package com.example.demo.Comment.controller;

import com.example.demo.Comment.dto.CommentResponseDto;
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
    public CommentResponseDto createComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.createComment(
                comment.getBoard().getPostId(),
                comment.getUser().getUserId(),
                comment.getContent(),
                comment.getParent() != null ? comment.getParent().getCommentId() : null
        );
        return commentService.convertToDto(savedComment);
    }

    // 특정 댓글 조회
    @GetMapping("/get/{id}")
    public Optional<CommentResponseDto> getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    // 모든 댓글 조회
    @GetMapping("/all")
    public List<CommentResponseDto> getAllComments() {
        return commentService.getAllComments();
    }

    // 특정 게시물에 속하는 모든 댓글 조회
    @GetMapping("/board/{boardId}")
    public List<CommentResponseDto> getCommentsByBoardId(@PathVariable Integer boardId) {
        return commentService.getCommentsByBoardId(boardId);
    }

    // 댓글 수정
    @PutMapping("/update/{id}")
    public CommentResponseDto updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(id, comment.getContent());
        return commentService.convertToDto(updatedComment);
    }

    // 댓글 삭제
    @DeleteMapping("/delete/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }
}
