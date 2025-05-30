package com.example.pidevbackendproject.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendMail(String to, String body) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("rifaesprit@gmail.com");
//        message.setTo(to);
//        message.setSubject("Added to subgroup notification");
//        message.setText(body);
//        mailSender.send(message);
//    }


    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to, String name, String subgroupName) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom("rifaesprit@gmail.com");
        helper.setTo(to);
        helper.setSubject("You’ve been added to a new subgroup!");

        String htmlContent = generateHtmlContent(name, subgroupName);
        helper.setText(htmlContent, true); // true indicates HTML

        mailSender.send(message);
    }

    private String generateHtmlContent(String name, String subgroupName) {
        return """
                <html>
                <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
                    <div style="max-width: 600px; margin: auto; background-color: white; border-radius: 10px; padding: 20px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                        <div style="text-align: center;">
                            <img src='https://i.imgur.com/amoLega.png' alt='Logo' style='width: 100px; margin-bottom: 20px;'/>
                            <h2 style="color: #333;">Hello %s 👋</h2>
                        </div>
                        <p style="font-size: 16px; color: #555;">
                            You have been added to a new subgroup named <strong>%s</strong>.
                        </p>
                        <p style="font-size: 14px; color: #777; margin-top: 30px;">
                            If you have any questions, feel free to reach out to our team.
                        </p>
                         <!-- Image de la signature -->
                    <div style="text-align: right; margin-top: 40px;">
                        <img src="https://i.imgur.com/RK2znLl.png" alt="Signature" style="width: 100px;" />
                    </div>
                        <hr style="margin: 30px 0;">
                        <p style="font-size: 12px; color: #aaa; text-align: center;">
                            &copy; 2025 MyTeamApp | Contact us: <a href="mailto:rifaesprit@gmail.com">rifaesprit@gmail.com</a>
                        </p>
                    </div>
                </body>
                </html>
                """.formatted(name, subgroupName);
    }
    
    public void sendMail(String to, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("rifaesprit@gmail.com");
            helper.setTo(to);
            helper.setSubject("Notification : VOUS AVEZ UNE NOTIFICATION ");
            helper.setText(htmlBody, true); // true = interpréter comme HTML

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
        }
    }
}