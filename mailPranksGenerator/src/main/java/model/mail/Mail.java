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

    public String getFrom() {
        return from;
    }

    public String[] getTo() {
        return to;
    }

    public String[] getCc() {
        return cc;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
