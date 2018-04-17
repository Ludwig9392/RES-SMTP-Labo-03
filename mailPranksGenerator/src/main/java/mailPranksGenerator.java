import config.ConfigManager;
import model.mail.Mail;
import smtp.SmtpClient;

import java.io.IOException;

public class mailPranksGenerator {

    // Attributes Test
    private static SmtpClient client;
    private static Mail email = new Mail();
    private static ConfigManager config;


    public static void main(String[] args) {
        // Test of a fully connection with the SMTP server (send a email with multiple receiver and CCs)
        try {
            config = new ConfigManager();
            client = new SmtpClient(config);

            // Email Test:
            email.setFrom("donald.trump@gmail.com");
            email.setTo(new String[] {"barack.obama@gmail.com", "michel.obama@gmail.com", "chirac.jacques@yahoo.fr"});
            email.setCc(new String[] {"chirac.jacques@yahoo.fr", "michel.obama@gmail.com"});
            email.setSubject("Nuclear Blast");
            email.setMessage("I will nuke your face, bastard !");

            client.sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}