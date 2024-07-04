package com.example.demo.User.service;

import com.example.demo.User.entity.User;
import com.example.demo.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) {
        // user.getUserId()가 null이 아닌 경우만 존재 여부 확인
        if (user.getUserId() != null && userRepository.existsById(user.getUserId())) {
            throw new IllegalArgumentException("UserId가 이미 존재합니다.");
        }
        if (user.getProfileImg() == null) {
            user.setProfileImg("basicprofileimg.jpg");
        }
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(Integer userId, User updatedUser) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setRole(updatedUser.getRole());
            user.setCohortId(updatedUser.getCohortId());
            user.setAssignedCohort(updatedUser.getAssignedCohort());
            user.setApprovalStatus(updatedUser.getApprovalStatus());
            user.setProfileImg(updatedUser.getProfileImg());
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    public void deleteUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        userOpt.ifPresent(userRepository::delete);
    }

    // 모든 회원 조회 메서드 추가
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
