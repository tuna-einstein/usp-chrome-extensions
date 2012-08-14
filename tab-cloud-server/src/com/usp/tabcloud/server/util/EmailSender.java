package com.usp.tabcloud.server.util;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    
    public void sendEmail(String from, String subject, String body, List<String> receipients) {
        Properties props = new Properties();
        Session mailSession = Session.getDefaultInstance(props, null);    
        Message msg = new MimeMessage(mailSession);
       
        try {
            msg.setFrom(new InternetAddress(from));
            for (String to : receipients) {
                msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            }
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}