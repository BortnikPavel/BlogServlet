package com.services.mailer;

/**
 * Created by Павел on 27.02.2017.
 */
import com.common.exceptions.MyException;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 * Created by admin on 14.02.2017.
 */
public class MailBilder {
    private static Logger logger = Logger.getLogger(MailBilder.class);
    public Multipart build(String messageText, String messageHtml)throws MyException{
            Multipart mpMixed = new MimeMultipart("mixed");
            addTextVersion(mpMixed, messageText);
            addHtmlVersion(mpMixed, messageHtml);
            return mpMixed;
    }
    private void addTextVersion(Multipart mpRelatedAlternative, String messageText)throws MyException {
        try {
            final MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(messageText, "text/plain");
            mpRelatedAlternative.addBodyPart(textPart);
        } catch (MessagingException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }

    private void addHtmlVersion(Multipart parent, String messageHtml) throws MyException {
        try {
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(messageHtml, "text/html");
        } catch (MessagingException e) {
            logger.error(e);
            throw new MyException("Sorry, we have some problem with our system!");
        }
    }
}