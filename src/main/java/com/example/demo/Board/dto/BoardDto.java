package com.example.demo.Board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardDto {
    private Integer postId;
    private String title;
    private String content;
    private String type;
}
