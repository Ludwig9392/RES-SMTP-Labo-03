import config.ConfigManager;
import model.mail.Mail;
import smtp.SmtpClient;

import java.io.IOException;

public class mailPranksGenerator {

    // Attribut Test
    private static SmtpClient client;
    private static Mail email = new Mail();
    private static ConfigManager config;


    public static void main(String[] args) {
        // Premier Test de connection partielle avec le serveur SMTP
        try {
            config = new ConfigManager();
            client = new SmtpClient(config);


            client.sendEmail(email);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
