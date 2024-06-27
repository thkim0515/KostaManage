package com.example.demo.UserProperties.controller;

import com.example.demo.UserProperties.entity.UserProperties;
import com.example.demo.UserProperties.service.UserPropertiesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_profits")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserPropertiesController {
    private final UserPropertiesService userPropertiesService ;

    public UserPropertiesController(UserPropertiesService userPropertiesService) {
        this.userPropertiesService = userPropertiesService;
    }

    // 수익 정보 추가
    @PostMapping("/add")
    public UserProperties addUserProfit(@RequestBody UserProperties userProperties) {
        System.out.println(userProperties);
        return userPropertiesService.addUserProfit(userProperties);
    }

    // 수익 정보 조회
    @GetMapping("/get/{profit_id}")
    public Optional<UserProperties> getUserProfitById(@PathVariable Integer profit_id) {
        return userPropertiesService.getUserProfitById(profit_id);
    }

    // 특정 사용자 수익 정보 조회
    @GetMapping("/user/{user_id}")
    public List<UserProperties> getUserProfitsByUserId(@PathVariable Integer user_id) {
        return userPropertiesService.getUserProfitsByUserId(user_id);
    }

    // 수익 정보 수정
    @PutMapping("/update/{profit_id}")
    public UserProperties updateUserProfit(@PathVariable Integer profit_id, @RequestBody UserProperties userProperties) {
        return userPropertiesService.updateUserProfit(profit_id, userProperties);
    }

    // 수익 정보 삭제
    @DeleteMapping("/delete/{profit_id}")
    public void deleteUserProfit(@PathVariable Integer profit_id) {
        userPropertiesService.deleteUserProfit(profit_id);
    }


    // 매도 시 수익 정보 업데이트
    @PostMapping("/sell/{profit_id}")
    public UserProperties sellUserProfit(@PathVariable Integer profit_id, @RequestBody SellRequest sellRequest) {
        return userPropertiesService.sellUserProfit(profit_id, sellRequest.getSellPrice());
    }
}

class SellRequest {
    private Double sellPrice;

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
