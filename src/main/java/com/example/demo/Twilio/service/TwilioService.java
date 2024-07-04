package com.example.demo.Twilio.service;

import com.example.demo.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class TwilioService {
    private final TwilioConfig twilioConfig;

    @Autowired
    public TwilioService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    @PostConstruct
    public void init() {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
    }

    public String sendMessage(String to, String message) {
        PhoneNumber toNumber = convertPhoneNumber(to);
        PhoneNumber fromNumber = new PhoneNumber(twilioConfig.getPhoneNumber());
        Message createdMessage = Message.creator(toNumber, fromNumber, message).create();
        return createdMessage.getSid();
    }

    private PhoneNumber convertPhoneNumber(String to) {
        if (to.startsWith("0")) {
            to = "+82" + to.substring(1);
        }
        return new PhoneNumber(to);
    }
}
