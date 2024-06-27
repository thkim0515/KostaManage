package com.example.demo.UserProperties.repository;

import com.example.demo.UserProperties.entity.UserProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPropertiesRepository extends JpaRepository<UserProperties, Integer> {
    List<UserProperties> findByUserId(Integer userId);
}
