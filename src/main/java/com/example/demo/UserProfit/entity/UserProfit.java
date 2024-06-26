package com.example.demo.UserProfit.entity;

import com.example.demo.User.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="user_profit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profit_id")
    private Integer profitId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;

    @Column(name = "current_price", nullable = false)
    private Double currentPrice;

    @Column(name = "market", nullable = false)
    private String market;

    @Column(name = "trade_status", nullable = false)
    private String tradeStatus;

    @Column(name = "last_updated", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdated;

    @Transient
    private Double profitRate;

    @Transient
    private Double profitAmount;

    @PostLoad
    @PostPersist
    @PostUpdate
    private void calculateProfit() {
        if (purchasePrice != null && currentPrice != null) {
            this.profitRate = (currentPrice - purchasePrice) / purchasePrice * 100;
            this.profitAmount = currentPrice - purchasePrice;
        }
    }

    @PrePersist
    @PreUpdate
    private void setLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }
}
