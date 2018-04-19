package smtp;

/**
 * This class defines constants for the SMTP Protocol
 *
 * @author Frueh Loïc, Dejvid Muaremi
 */

public class SmtpProtocol {
    public final static int DEFAULT_PORT = 25;

    public final static String CMD_HELLO = "EHLO";
    public final static String CMD_MAIL = "MAIL FROM:";
    public final static String CMD_RCPT = "RCPT TO:";
    public final static String CMD_DATA = "DATA";
    public final static String CMD_QUIT = "quit";

    public final static String FROM = "From:";
    public final static String TO = "To:";
    public final static String CC = "Cc:";
    public final static String SUBJECT = "Subject:";

    public final static String RETURN = "\r\n";
    public final static String END_MESSAGE = "\r\n.\r\n";
    public final static String ENCODAGE_UTF8 = "Content-Type: text/plain; charset=UTF-8";


}
