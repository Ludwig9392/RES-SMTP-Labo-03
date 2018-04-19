package smtp;

import model.mail.Mail;
import java.io.IOException;

/***
 * This interface is used to send an email with a smtp client.
 * The implementation of the interface decides how the email should be sent.
 *
 * @author Loïc Frueh, Dejvid Muaremi
 */
public interface ISmtpClient {

    /***
     * When this method is invoked it will send an email.
     * @param email the email to send to a smtp server.
     * @throws IOException when a reader or a writer has a IO exception.
     */
    public void sendEmail(Mail email) throws IOException;
}
