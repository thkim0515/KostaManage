package com.example.demo.Twilio.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class TwilioService {
    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.phoneNumber}")
    private String fromPhoneNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    public String sendMessage(String to, String message) {
        PhoneNumber toNumber = new PhoneNumber(to.replaceFirst("^0", "+82"));
        PhoneNumber fromNumber = new PhoneNumber(fromPhoneNumber);
        Message createdMessage = Message.creator(toNumber, fromNumber, message).create();
        return createdMessage.getSid();
    }
}
