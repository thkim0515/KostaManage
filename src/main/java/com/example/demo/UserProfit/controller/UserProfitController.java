package com.example.demo.UserProfit.controller;

import com.example.demo.UserProfit.entity.UserProfit;
import com.example.demo.UserProfit.service.UserProfitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_profits")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserProfitController {
    private final UserProfitService userProfitService;

    public UserProfitController(UserProfitService userProfitService) {
        this.userProfitService = userProfitService;
    }

    // 수익 정보 추가
    @PostMapping("/add")
    public UserProfit addUserProfit(@RequestBody UserProfit userProfit) {
        return userProfitService.addUserProfit(userProfit);
    }

    // 수익 정보 조회
    @GetMapping("/get/{profit_id}")
    public Optional<UserProfit> getUserProfitById(@PathVariable Integer profit_id) {
        return userProfitService.getUserProfitById(profit_id);
    }

    // 특정 사용자 수익 정보 조회
    @GetMapping("/user/{user_id}")
    public List<UserProfit> getUserProfitsByUserId(@PathVariable Integer user_id) {
        return userProfitService.getUserProfitsByUserId(user_id);
    }

    // 수익 정보 수정
    @PutMapping("/update/{profit_id}")
    public UserProfit updateUserProfit(@PathVariable Integer profit_id, @RequestBody UserProfit userProfit) {
        return userProfitService.updateUserProfit(profit_id, userProfit);
    }

    // 수익 정보 삭제
    @DeleteMapping("/delete/{profit_id}")
    public void deleteUserProfit(@PathVariable Integer profit_id) {
        userProfitService.deleteUserProfit(profit_id);
    }
}
