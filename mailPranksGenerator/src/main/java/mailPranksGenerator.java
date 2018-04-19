import config.ConfigManager;
import config.IConfigManager;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.ISmtpClient;
import smtp.SmtpClient;
import java.io.IOException;
import java.util.List;
import java.util.logging.*;

public class mailPranksGenerator {
    private static final Logger LOG = Logger.getLogger(mailPranksGenerator.class.getName());

    public static void main(String[] args) {
        /*
         * I prefer to have LOG output on a single line, it's easier to read. Being able
         * to change the formatting of console outputs is one of the reasons why it is
         * better to use a Logger rather than using System.out.println
         */
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");
        
        // Test of the PrankGenerator class
        try {
            IConfigManager config = new ConfigManager();
            PrankGenerator generator = new PrankGenerator(config);
            List<Prank> pranks = generator.generatePranks();

            ISmtpClient client = new SmtpClient(config);
            for (Prank prank : pranks) {
                client.sendEmail(prank.toForgedMail());
            }

        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Could not send the pranks. {0}", e.getMessage());
            e.printStackTrace();
        }
    }
}