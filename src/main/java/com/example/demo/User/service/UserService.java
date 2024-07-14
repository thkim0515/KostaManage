package com.example.demo.User.service;

import com.example.demo.User.entity.User;
import com.example.demo.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        if (user.getUserId() != null && userRepository.existsById(user.getUserId())) {
            throw new IllegalArgumentException("UserId가 이미 존재합니다.");
        }
        if (user.getProfileImg() == null) {
            user.setProfileImg("basicprofileimg.jpg");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByNameWithCohort(name);
    }

    public User updateUser(Integer userId, User updatedUser) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            user.setRole(updatedUser.getRole());
            user.setCohort(updatedUser.getCohort());
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

    public void softDeleteUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setIsDeleted(true);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAllActive();
    }

    public List<User> findUsersByCohortId(Integer cohortId) {
        return userRepository.findByCohortId(cohortId);
    }
}
