package model.prank;

import model.mail.Group;
import model.mail.Mail;
import model.mail.Person;
import smtp.SmtpProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a Prank. A Prank, in that case, is a forged e-mail which appear
 * to be sent by certain people but in reality is sent by another.
 *
 * @author Frueh Loïc, Dejvid Muaremi
 */
public class Prank {

    private Person fakeSender = new Person();
    private Group receivers = new Group();
    private Group witnesses = new Group();
    private String message;
    
    /***
     * Create a Person from the fake sender and return it.
     * @return a fake sender as a Person.
     */
    public Person getFakeSender() {
        return new Person(fakeSender.getFirstName(), fakeSender.getLastName(), fakeSender.getEmail());
    }
    
    /***
     * Create a Group of receivers and return it.
     * @return a Group of receivers.
     */
    public Group getReceivers() {
        Group receivers = new Group();
        receivers.addMultiplePersons(this.receivers.getGroup());
        return receivers;
    }
    
    /***
     * Create a Group of witnesses and return it.
     * @return a Group of witnesses.
     */
    public Group getWitnesses() {
        Group witnesses = new Group();
        witnesses.addMultiplePersons(this.witnesses.getGroup());
        return witnesses;
    }
    
    /***
     * Get the message of the prank.
     * @return the message from the prank.
     */
    public String getMessage() {
        return message;
    }
    
    /***
     * Set a fake sender from a Person.
     * @param fakeSender a Person that we need to copy
     */
    public void setFakeSender(Person fakeSender) {
        this.fakeSender.setFirstName(fakeSender.getFirstName());
        this.fakeSender.setLastName(fakeSender.getLastName());
        this.fakeSender.setEmail(fakeSender.getEmail());
    }
    
    /***
     * Set a Group of receivers from a Group of Person.
     * @param receivers a Group of Person that we need to copy
     */
    public void setReceivers(Group receivers) {
        this.receivers.setGroup(receivers.getGroup());
    }
    
    /***
     * Set a Group of witnesses from a Group of Person.
     * @param witnesses a Group of Person that we need to copy
     */
    public void setWitnesses(Group witnesses) {
        this.witnesses.setGroup(witnesses.getGroup());
    }
    
    /***
     * Set the message that will be sent by the sender to the receiver.
     * @param message a message that will be sent.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /***
     * Add a receiver to the Group of receivers
     * @param receiver the Person to add to the Group
     */
    public void addReceiver(Person receiver) {
        receivers.addPerson(receiver);
    }
    
    /***
     * Add a Group of receiver to the current Group of receivers
     * @param receivers the Group to add to the current Group.
     */
    public void addReceivers(Group receivers) {
        this.receivers.addMultiplePersons(receivers.getGroup());
    }
    
    /***
     * Add a witness to the Group of witnesses
     * @param witness the Person to add to the Group
     */
    public void addWitness(Person witness) {
        receivers.addPerson(witness);
    }
    
    /***
     * Add a Group of witnesses to the current Group of witnesses
     * @param witnesses the Group to add to the current Group.
     */
    public void addWitnesses(Group witnesses) {
        this.witnesses.addMultiplePersons(witnesses.getGroup());
    }
    
    /***
     * Create a forged email that contain a prank.
     * @return a forged mail with a prank.
     */
    public Mail toForgedMail() {
        Mail email = new Mail();

        email.setFrom(fakeSender.getEmail());

        List<String> to = new ArrayList<String>();
        for (Person p : receivers.getGroup()) {
            to.add(p.getEmail());
        }

        email.setTo(to.toArray(new String[to.size()]));

        List<String> cc = new ArrayList<String>();
        for (Person p : witnesses.getGroup()) {
            cc.add(p.getEmail());
        }

        email.setCc(cc.toArray(new String[cc.size()]));

        email.setMessage(message + SmtpProtocol.RETURN + fakeSender.getLastName() + " " + fakeSender.getFirstName());

        return email;
    }
}
