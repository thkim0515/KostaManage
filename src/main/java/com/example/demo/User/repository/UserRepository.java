package com.example.demo.User.repository;

import com.example.demo.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByName(String name);

    List<User> findByCohortId(Integer cohortId);

    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    List<User> findAllActive();
}
