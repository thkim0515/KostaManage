package com.example.demo.Board.dto;

import com.example.demo.User.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardResponseDto {
    private Integer postId;
    private String title;
    private String content;
    private String type;
    private UserDto user;
    private Integer cohortId;
    private Integer views;
    private Integer likes;
    private String createdAt;
    private String updatedAt;
    private Boolean isDeleted;
}
