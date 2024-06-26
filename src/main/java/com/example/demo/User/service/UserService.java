package com.example.demo.User.service;

import com.example.demo.User.entity.User;
import com.example.demo.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User register(User user) {
        if (userRepository.findByUserId(user.getUserId()).isPresent()) {
            throw new IllegalArgumentException("UserId가 이미 존재합니다.");
        }
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(Integer userId, User updatedUser) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUserPassword(updatedUser.getUserPassword());
            user.setUserNickname(updatedUser.getUserNickname());
            user.setUserEmail(updatedUser.getUserEmail());
            user.setUserProfileImg(updatedUser.getUserProfileImg());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    public void deleteUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        userOpt.ifPresent(userRepository::delete);
    }
}
