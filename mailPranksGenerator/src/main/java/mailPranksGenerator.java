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
    private static Mail email;


    public static void main(String[] args) {
        // Test of the correct config file importation
        try {
            config = new ConfigManager();
            client = new SmtpClient(config);

            Group victims = config.getVictims();
            victims.shuffle();
            prank.setFakeSender(victims.removePerson(0));
            prank.setReceivers(victims);

            Group cc = config.getWitnesses();
            prank.setWitnesses(cc);

            prank.setMessage(config.getMessages().remove(0));

            email = prank.toForgedMail();
            client.sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}