package com.example.demo.config;

public class TwilioConfig {
    private final String accountSid;
    private final String authToken;
    private final String phoneNumber;

    public TwilioConfig(String accountSid, String authToken, String phoneNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
