package com.example.demo.UserProfit.repository;

import com.example.demo.UserProfit.entity.UserProfit;
import com.example.demo.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProfitRepository extends JpaRepository<UserProfit, Integer> {
    List<UserProfit> findByUser(User user);
}
