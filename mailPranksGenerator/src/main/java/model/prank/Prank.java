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
 * @author Frueh Loïc
 */
public class Prank {

    private Person fakeSender = new Person();
    private Group receivers = new Group();
    private Group witnesses = new Group();

    private String message;

    public Person getFakeSender() {
        return new Person(fakeSender.getFirstName(), fakeSender.getLastName(), fakeSender.getEmail());
    }

    public Group getReceivers() {
        Group receivers = new Group();
        receivers.addMultiplePersons(this.receivers.getGroup());
        return receivers;
    }

    public Group getWitnesses() {
        Group witnesses = new Group();
        witnesses.addMultiplePersons(this.witnesses.getGroup());
        return witnesses;
    }

    public String getMessage() {
        return message;
    }

    public void setFakeSender(Person fakeSender) {
        this.fakeSender.setFirstName(fakeSender.getFirstName());
        this.fakeSender.setLastName(fakeSender.getLastName());
        this.fakeSender.setEmail(fakeSender.getEmail());
    }

    public void setReceivers(Group receivers) {
        this.receivers.setGroup(receivers.getGroup());
    }

    public void setWitnesses(Group witnesses) {
        this.witnesses.setGroup(witnesses.getGroup());
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addReceiver(Person receiver) {
        receivers.addPerson(receiver);
    }

    public void addReceivers(Group receivers) {
        this.receivers.addMultiplePersons(receivers.getGroup());
    }

    public void addWitness(Person witness) {
        receivers.addPerson(witness);
    }

    public void addWitnesses(Group witnesses) {
        this.witnesses.addMultiplePersons(witnesses.getGroup());
    }

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
