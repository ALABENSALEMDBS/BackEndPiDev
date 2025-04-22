package com.example.pidevbackendproject.services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

 /*   public void sendMail(String to, String body) {  try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abbessawssen23@gmail.com");
        message.setTo(to);
        message.setSubject("Added to subgroup notification");
        message.setText(body);
        mailSender.send(message);
    } catch (Exception e) {
        // Log l'exception pour voir pourquoi l'email ne part pas
        System.out.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
    }
    }*/

    public void sendMail(String to, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("abbessawssen23@gmail.com");
            helper.setTo(to);
            helper.setSubject("Notification : VOUS AVEZ UN EXERCICE");
            helper.setText(htmlBody, true); // true = interpréter comme HTML

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
        }
    }

}
