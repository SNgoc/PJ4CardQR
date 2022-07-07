package com.example.demo.Email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements IEmailSender{
    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailService.class);

    private final JavaMailSender emailSender;
    @Override
    @Async
    public void send(String to, String subject,String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nghiapham1998000@gmail.com");
        message.setTo(to);
        message.setText(body);
        message.setSubject(subject);
        emailSender.send(message);
        System.out.println("Mail send success fully");
    }
}
