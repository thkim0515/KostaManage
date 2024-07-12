package com.example.demo.Comment.dto;

import com.example.demo.Board.dto.BoardDto;
import com.example.demo.User.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponseDto {
    private Integer commentId;
    private Integer boardId;
    private UserDto user;
    private String content;
    private CommentResponseDto parent;
    private LocalDateTime createdAt;
}

