package com.example.demouser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
private final JavaMailSender javaMailSender;

public void sendMail(String mail, String subject, String content){
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(mail);
    message.setSubject(subject);
    message.setText(content);
    javaMailSender.send(message);
}
}
