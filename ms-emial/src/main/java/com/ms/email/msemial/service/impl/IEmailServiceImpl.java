package com.ms.email.msemial.service.impl;

import com.ms.email.msemial.dto.jms.JmsEmailDetails;
import com.ms.email.msemial.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class IEmailServiceImpl implements IEmailService {

    @Value("${spring.mail.username}") private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(JmsEmailDetails details) {

        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.printf(e.getMessage());
        }

    }

}
