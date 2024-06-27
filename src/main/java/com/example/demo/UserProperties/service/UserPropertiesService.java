package com.example.demo.UserProperties.service;

import com.example.demo.UserProperties.entity.UserProperties;
import com.example.demo.UserProperties.repository.UserPropertiesRepository;
import com.example.demo.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserPropertiesService {
    private final UserPropertiesRepository userPropertiesRepository;
    private final UserRepository userRepository;

    public UserPropertiesService(UserPropertiesRepository userPropertiesRepository, UserRepository userRepository) {
        this.userPropertiesRepository = userPropertiesRepository;
        this.userRepository = userRepository;
    }

    public UserProperties addUserProfit(UserProperties userProperties) {
        if (userRepository.existsById(userProperties.getUserId())) {
            userProperties.setPurchaseDate(LocalDateTime.now());  // 현재 시간으로 설정
            userProperties.setLastUpdated(LocalDateTime.now());
            return userPropertiesRepository.save(userProperties);
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    public Optional<UserProperties> getUserProfitById(Integer profitId) {
        return userPropertiesRepository.findById(profitId);
    }

    public List<UserProperties> getUserProfitsByUserId(Integer userId) {
        if (userRepository.existsById(userId)) {
            return userPropertiesRepository.findByUserId(userId);
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    public UserProperties updateUserProfit(Integer profitId, UserProperties updatedUserProperties) {
        Optional<UserProperties> profitOpt = userPropertiesRepository.findById(profitId);
        if (profitOpt.isPresent()) {
            UserProperties userProperties = profitOpt.get();
            userProperties.setAssetName(updatedUserProperties.getAssetName());
            userProperties.setPurchasePrice(updatedUserProperties.getPurchasePrice());
            userProperties.setMarket(updatedUserProperties.getMarket());
            userProperties.setTradeStatus(updatedUserProperties.getTradeStatus());
            userProperties.setLastUpdated(LocalDateTime.now());
            return userPropertiesRepository.save(userProperties);
        } else {
            throw new IllegalArgumentException("수익 정보를 찾을 수 없습니다.");
        }
    }

    public void deleteUserProfit(Integer profitId) {
        Optional<UserProperties> profitOpt = userPropertiesRepository.findById(profitId);
        profitOpt.ifPresent(userPropertiesRepository::delete);
    }

    public UserProperties sellUserProfit(Integer profitId, Double sellPrice) {
        Optional<UserProperties> profitOpt = userPropertiesRepository.findById(profitId);
        if (profitOpt.isPresent()) {
            UserProperties userProperties = profitOpt.get();
            userProperties.setTradeStatus("매도");
            userProperties.calculateProfit(sellPrice);
            userProperties.setLastUpdated(LocalDateTime.now());
            return userPropertiesRepository.save(userProperties);
        } else {
            throw new IllegalArgumentException("수익 정보를 찾을 수 없습니다.");
        }
    }
}
