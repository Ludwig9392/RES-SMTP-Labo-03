package model.mail;

/**
 * A class that represents a Mail. A Mail is defined by all its inputs
 * (from, to, cc, message, etc...)
 *
 * @author Frueh Loïc, Dejvid Muaremi
 */
public class Mail {
    private String from;
    private String[] to;
    private String[] cc;
    private String subject;
    private String message;
    
    /***
     * Get the email address of the sender of a mail.
     * @return the email address of the sender .
     */
    public String getFrom() {
        return from;
    }
    
    /***
     * Get the email address of the receivers of a mail.
     * @return an array of email address of the receivers.
     */
    public String[] getTo() {
        return to;
    }
    
    /***
     * Get all the email address of the cc of a mail.
     * @return an array of email address of the cc
     */
    public String[] getCc() {
        return cc;
    }
    
    /***
     * Get the subject of a mail.
     * @return the subject of a mail.
     */
    public String getSubject() {
        return subject;
    }
    
    /***
     * Get the message of a mail.
     * @return the message of a mail
     */
    public String getMessage() {
        return message;
    }
    
    /***
     * Set the email address of the sender of the mail.
     * @param from the sender of a mail.
     */
    public void setFrom(String from) {
        this.from = from;
    }
    
    /***
     * Set the email addresses of the receivers of the mail.
     * @param to an array of the receivers of a mail.
     */
    public void setTo(String[] to) {
        this.to = to;
    }
    
    /***
     * Set the email addresses of the cc of the mail.
     * @param cc an array of email addresses of the cc.
     */
    public void setCc(String[] cc) {
        this.cc = cc;
    }
    
    /***
     * Set the subject of the mail.
     * @param subject the subject of a mail.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    /***
     * Set the message of the mail.
     * @param message the message of a mail.
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
