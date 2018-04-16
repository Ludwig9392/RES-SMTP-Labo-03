package model.mail;

/**
 * A class that represents a Mail. A Mail is defined by all its inputs
 * (from, to, cc, message, etc...)
 *
 * @author Frueh Loïc
 */
public class Mail {
    private String from;
    private String[] to;
    private String[] cc;
    private String subject;
    private String message;
}
