package com.example.demo.SecretManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecretController {

    @Value("${manageKey}")
    private String secretKey;

    @GetMapping("/secret")
    public String getSecret() {
        return secretKey;
    }
}