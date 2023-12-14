package com.app.shopping.ecommerce.services.impl;// EmailServiceImpl.java
import com.app.shopping.ecommerce.services.EmailService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${email.app.password}")
    private String password;
//@Autowired
//private JavaMailSender javaMailSender;
    @Override
    public void sendPinToUser(String email, String pin) {
        // Implement your email sending logic here
        // This is a placeholder, you should use a proper email sending library or service
        System.out.println("Sending PIN to " + email + ": " + pin);
        String from = "ravibala220022@gmail.com"; // Replace with your Gmail address
        String password = this.password; // Replace with your Gmail password

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.timeout", "5000"); // 5 seconds timeout
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("Password Reset PIN");
            message.setText("Your PIN for password reset: " + pin);

            Transport.send(message);
//javaMailSender.send(message);
            System.out.println("Email sent successfully to " + email);
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
