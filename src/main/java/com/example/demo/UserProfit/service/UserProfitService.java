package com.example.demo.UserProfit.service;

import com.example.demo.User.entity.User;
import com.example.demo.User.repository.UserRepository;
import com.example.demo.UserProfit.entity.UserProfit;
import com.example.demo.UserProfit.repository.UserProfitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfitService {
    private final UserProfitRepository userProfitRepository;
    private final UserRepository userRepository;

    public UserProfitService(UserProfitRepository userProfitRepository, UserRepository userRepository) {
        this.userProfitRepository = userProfitRepository;
        this.userRepository = userRepository;
    }

    public UserProfit addUserProfit(UserProfit userProfit) {
        Optional<User> user = userRepository.findById(userProfit.getUser().getId());
        if (user.isPresent()) {
            userProfit.setUser(user.get());
            userProfit.setLastUpdated(LocalDateTime.now()); // lastUpdated를 설정합니다.
            return userProfitRepository.save(userProfit);
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    public Optional<UserProfit> getUserProfitById(Integer profitId) {
        return userProfitRepository.findById(profitId);
    }

    public List<UserProfit> getUserProfitsByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(userProfitRepository::findByUser).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public UserProfit updateUserProfit(Integer profitId, UserProfit updatedUserProfit) {
        Optional<UserProfit> profitOpt = userProfitRepository.findById(profitId);
        if (profitOpt.isPresent()) {
            UserProfit userProfit = profitOpt.get();
            userProfit.setAssetName(updatedUserProfit.getAssetName());
            userProfit.setPurchaseDate(updatedUserProfit.getPurchaseDate());
            userProfit.setPurchasePrice(updatedUserProfit.getPurchasePrice());
            userProfit.setCurrentPrice(updatedUserProfit.getCurrentPrice());
            userProfit.setMarket(updatedUserProfit.getMarket());
            userProfit.setTradeStatus(updatedUserProfit.getTradeStatus());
            userProfit.setLastUpdated(LocalDateTime.now());
            return userProfitRepository.save(userProfit);
        } else {
            throw new IllegalArgumentException("수익 정보를 찾을 수 없습니다.");
        }
    }

    public void deleteUserProfit(Integer profitId) {
        Optional<UserProfit> profitOpt = userProfitRepository.findById(profitId);
        profitOpt.ifPresent(userProfitRepository::delete);
    }
}
