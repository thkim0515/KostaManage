package com.example.demo.User.controller;

import com.example.demo.User.entity.User;
import com.example.demo.User.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // 회원 정보 조회
    @GetMapping("/get/{user_id}")
    public Optional<User> getUserById(@PathVariable Integer user_id) {
        return userService.findById(user_id);
    }

    // 회원 정보 수정
    @PutMapping("/update/{user_id}")
    public User updateUser(@PathVariable Integer user_id, @RequestBody User user) {
        return userService.updateUser(user_id, user);
    }

    // 회원 삭제
    @DeleteMapping("/delete/{user_id}")
    public void deleteUser(@PathVariable Integer user_id) {
        userService.deleteUser(user_id);
    }
}
