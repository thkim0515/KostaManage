package com.example.demo.config;

public class JWTConfig {
    private final String secretKey;
    private final Integer expiration;

    public JWTConfig(String secretKey, Integer expiration) {
        this.secretKey = secretKey;
        this.expiration = expiration;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Integer getExpiration() {
        return expiration;
    }
}
