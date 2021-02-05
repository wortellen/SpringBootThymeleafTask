package com.netcracker.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Controller
public class SendEmailController {

    @GetMapping("/send-email")
    public String sendEmail(@CookieValue(value = "email") String email) throws MessagingException {

        String subject = "Hello";
        String msg ="Hello from NetCracker!";
        final String from ="vadim.zel15@gmail.com";
        final  String password ="dflbvrf1";
        Properties properties = new Properties();
        properties.setProperty("spring.mail.properties.mail.smtp.starttls.enable","true");
        properties.setProperty("spring.mail.properties.mail.smtp.auth","true");
        properties.setProperty("mail.transport.protocol", "smtps");
        properties.setProperty("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(from);

        MimeMessage message = new MimeMessage(session);
        message.setSender(addressFrom);
        message.setSubject(subject);
        message.setContent(msg, "text/plain");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

        transport.connect();
        Transport.send(message);
        transport.close();

        return "send-email";
    }
}
