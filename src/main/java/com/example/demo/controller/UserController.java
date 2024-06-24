package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // 회원 가입
    @PostMapping("/register")
    public User register(@RequestBody User user){
        System.out.println(user);
        return userService.register(user.getUserId(),user.getUserPassword(),user.getUserNickname());
    }

    // 회원 정보 조회
    @GetMapping("/get/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    // 회원 정보 수정
    @PutMapping("/update/{username}")
    public User updateUser(@PathVariable String username, @RequestBody User user) {
        return userService.updateUser(username, user.getUserPassword(), user.getUserNickname());
    }

    // 회원 삭제
    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}
