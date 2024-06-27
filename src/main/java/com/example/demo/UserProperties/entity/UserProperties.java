package com.example.demo.UserProperties.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="user_properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "properties_id")
    private Integer propertiesId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "purchase_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime purchaseDate;

    @Column(name = "purchase_price", nullable = false)
    private Double purchasePrice;

    @Column(name = "market", nullable = false)
    private String market;

    @Column(name = "trade_status", nullable = false)
    private String tradeStatus;

    @Column(name = "last_updated", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdated;

    @Column(name = "profit_rate", nullable = false, columnDefinition = "DECIMAL(5, 2) DEFAULT 0.00")
    private Double profitRate = 0.00;

    @Column(name = "profit_amount", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT 0.00")
    private Double profitAmount = 0.00;

    public void calculateProfit(Double sellPrice) {
        if (purchasePrice != null && sellPrice != null) {
            this.profitRate = (sellPrice - purchasePrice) / purchasePrice * 100;
            this.profitAmount = sellPrice - purchasePrice;
        }
    }
}
