package com.services.mailer;

/**
 * Created by Павел on 27.02.2017.
 */

import com.common.exceptions.MyException;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Sender {
    private static Logger logger = Logger.getLogger(Sender.class);
    private final String username = "bortnikpp@gmail.com";
    private final String password = "Pashabirthday15962841988";
    private String to;
    private final String smtpServer = "smtp.gmail.com";
    private final String smtpPort = "587";

    public void setTo(String to) {
        this.to = to;
    }

    public void send() throws MyException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", smtpServer);
        props.put("mail.smtp.ssl.trust", smtpServer);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Testing Subject");
            String messageText = "There was a login under your admin account, if it's not you. Refer to support us .....";
            String messageHtml = "There was a login under your admin account, if it's not you. Refer to support us .....";
            MailBilder mailBuilder = new MailBilder();
            Multipart mpMixed = mailBuilder.build(messageText, messageHtml);
            message.setContent(mpMixed);
            System.out.println("Sending");
            Transport.send(message);
            System.out.println("Done");
        }catch (MessagingException e){
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }
}


