import config.ConfigManager;
import config.IConfigManager;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.ISmtpClient;
import smtp.SmtpClient;
import java.io.IOException;
import java.util.List;

public class mailPranksGenerator {

    public static void main(String[] args) {
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
            e.printStackTrace();
        }
    }
}