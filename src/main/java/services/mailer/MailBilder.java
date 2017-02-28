package services.mailer;

/**
 * Created by Павел on 27.02.2017.
 */
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * Created by admin on 14.02.2017.
 */
public class MailBilder {
    public Multipart build(String messageText, String messageHtml) throws MessagingException {
        Multipart mpMixed = new MimeMultipart("mixed");
        addTextVersion(mpMixed, messageText);
        addHtmlVersion(mpMixed, messageHtml);
        return mpMixed;
    }
    private void addTextVersion(Multipart mpRelatedAlternative, String messageText) throws MessagingException {
        final MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(messageText, "text/plain");
        mpRelatedAlternative.addBodyPart(textPart);
    }

    private void addHtmlVersion(Multipart parent, String messageHtml) throws MessagingException {
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(messageHtml, "text/html");
    }
}