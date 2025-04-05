package com.example.pidevbackendproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rifaesprit@gmail.com");
        message.setTo(to);
        message.setSubject("Added to subgroup notification");
        message.setText(body);
        mailSender.send(message);
    }
}
