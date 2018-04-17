import config.ConfigManager;
import model.mail.Group;
import model.mail.Mail;
import model.mail.Person;
import model.prank.Prank;
import smtp.SmtpClient;

import java.io.IOException;

public class mailPranksGenerator {

    // Attributes Test
    private static SmtpClient client;
    private static ConfigManager config;
    private static Prank prank = new Prank();


    public static void main(String[] args) {
        // Test of the correct prank to mail generation
        try {
            config = new ConfigManager();
            client = new SmtpClient(config);

            // Prank Test:
            prank.setFakeSender(new Person("Loic", "Frueh","loic.frueh@gmail.com"));

            Group to = new Group();
            to.addMultiplePersons(new Person("qwe", "qas", "thor@gmail.com"),
                    new Person("aaa", "bbb", "hulk@gmail.com"), new Person("qqq", "wwww", "ironman@gmail.com"),
                    new Person("ddd", "we", "strange@gmail.com"));

            prank.setReceivers(to);

            Group cc = new Group();
            to.addMultiplePersons(new Person("aaa", "bbb", "hulk@gmail.com"),
                    new Person("ddd", "we", "strange@gmail.com"));

            prank.setWitnesses(cc);

            prank.setMessage("Ceci est un prank haha et Hulk est bien plus fort que Thor !");


            client.sendEmail(prank.toForgedMail());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}