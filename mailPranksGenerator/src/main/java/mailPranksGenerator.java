import config.*;
import model.prank.*;
import smtp.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.*;

/***
 * This application is used to create pranks from a config file.
 * Then these pranks are used to send emails.
 * The emails are sent from a victim to a list of victims.
 *
 * @author Loïc Frueh, Dejvid Muaremi
 */
public class mailPranksGenerator {
    private static final Logger LOG = Logger.getLogger(mailPranksGenerator.class.getName());

    public static void main(String[] args) {
        /*
         * I prefer to have LOG output on a single line, it's easier to read. Being able
         * to change the formatting of console outputs is one of the reasons why it is
         * better to use a Logger rather than using System.out.println
         */
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");
        IConfigManager configManager;
        PrankGenerator prankGenerator;
        List<Prank> pranks;
        ISmtpClient smtpClient;
        try {
            configManager = new ConfigManager();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Could not read the config file. {0}", e.getMessage());
            e.printStackTrace();
            return;
        }
        
        prankGenerator = new PrankGenerator(configManager);
        pranks = prankGenerator.generatePranks();
        smtpClient = new SmtpClient(configManager);
        
        try {
            for (Prank prank : pranks) {
                smtpClient.sendEmail(prank.toForgedMail());
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Could not send the pranks. {0}", e.getMessage());
            e.printStackTrace();
        }
    }
}