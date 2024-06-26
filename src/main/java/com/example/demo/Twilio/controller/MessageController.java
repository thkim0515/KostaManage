package com.example.demo.Twilio.controller;

import com.example.demo.Twilio.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send-message")
public class MessageController {
    @Autowired
    private TwilioService twilioService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody MessageRequest messageRequest) {
        try {
            String sid = twilioService.sendMessage(messageRequest.getTo(), messageRequest.getMessage());
            return ResponseEntity.ok("Message sent with SID: " + sid);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send message: " + e.getMessage());
        }
    }

    public static class MessageRequest {
        private String to;
        private String message;

        // Getters and setters
        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
