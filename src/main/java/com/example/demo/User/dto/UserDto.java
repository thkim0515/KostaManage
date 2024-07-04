package com.example.demo.User.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private Integer userId;
    private String name;
    private String email;
    private String profileimg;
}

