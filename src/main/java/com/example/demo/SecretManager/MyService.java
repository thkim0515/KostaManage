package com.example.demo.SecretManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Value("${manageKey}")
    private String manageKey;

    public void printManageKey() {
        System.out.println("Manage Key: " + manageKey);
    }
}