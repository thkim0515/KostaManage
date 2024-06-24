package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User register(String userId, String userPassword, String userNickname){
        System.out.println("User ID: " + userId);
        System.out.println("User Password: " + userPassword);
        System.out.println("User Nickname: " + userNickname);
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("아이디");
        }
        if (userPassword == null || userPassword.isEmpty()) {
            throw new IllegalArgumentException("패스워드");
        }
        if (userNickname == null || userNickname.isEmpty()) {
            throw new IllegalArgumentException("닉네임");
        }
        User user = new User(userId,userPassword,userNickname);
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUserId(username);
    }

    public User updateUser(String username, String userPassword, String userNickname) {
        Optional<User> userOpt = userRepository.findByUserId(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUserPassword(userPassword);
            user.setUserNickname(userNickname);
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    public void deleteUser(String username) {
        Optional<User> userOpt = userRepository.findByUserId(username);
        userOpt.ifPresent(userRepository::delete);
    }
}
