package com.SimpleTaskManager.TaskManager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioNumber;

    public void sendSms(String to, String messageBody) {

        Twilio.init(accountSid, authToken);

        try {
            Message.creator(
                    new com.twilio.type.PhoneNumber(to),
                    new com.twilio.type.PhoneNumber(twilioNumber),
                    messageBody
            ).create();

            System.out.println("SMS successfully sent");

        } catch (Exception e) {
            System.out.println("SMS Failed: " + e.getMessage());
        }

    }
}
