package smtp;

import config.IConfigManager;
import model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.logging.*;

/**
 * A SMTP client that can send e-mails through a SMTP server.
 *
 * @author Frueh Loïc
 */
public class SmtpClient implements ISmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private Socket socket;
    private int serverListenPort = SmtpProtocol.DEFAULT_PORT;
    private String serverAdresse;

    private BufferedReader input;
    private PrintWriter output;


    public SmtpClient(String serverAdresse) {
        this.serverAdresse = serverAdresse;
    }

    public SmtpClient(String serverAdresse, int serverListenPort) {
        this.serverAdresse = serverAdresse;
        this.serverListenPort = serverListenPort;
    }

    public SmtpClient(IConfigManager config) {
        this.serverAdresse = config.getSmtpServerAddress();
        this.serverListenPort = config.getSmtpServerListenPort();
    }

    public void sendEmail(Mail email) throws IOException {
        socket = new Socket(serverAdresse, serverListenPort);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Test of implementation for a full connection with the SMTP Server (with multiple receiver and CCs)

        LOG.log(Level.INFO, input.readLine());
        LOG.info("Test EHLO ------------------------");
        // Beware: Do not use println() --> not recognized by server
        output.print(SmtpProtocol.CMD_HELLO + " Kitty" + SmtpProtocol.RETURN);
        output.flush();

        String serverResponse;
        while(!(serverResponse = input.readLine()).substring(0,4).equals("250 ")) {
            LOG.log(Level.INFO, serverResponse);
        }
        LOG.log(Level.INFO, serverResponse);
    
        LOG.info("MAIL FROM ------------------------");
        output.print(SmtpProtocol.CMD_MAIL + email.getFrom() + SmtpProtocol.RETURN);
        output.flush();
        LOG.log(Level.INFO, input.readLine());
    
        LOG.info("RCPT TO --------------------------");
        for (String receiver : email.getTo()) {
            output.print(SmtpProtocol.CMD_RCPT + receiver + SmtpProtocol.RETURN);
            output.flush();
            LOG.log(Level.INFO, input.readLine());
        }

        for (String cc : email.getCc()) {
            output.print(SmtpProtocol.CMD_RCPT + cc + SmtpProtocol.RETURN);
            output.flush();
            LOG.log(Level.INFO, input.readLine());
        }

        LOG.info("DATA -----------------------------");
        output.print(SmtpProtocol.CMD_DATA + SmtpProtocol.RETURN);
        output.flush();
        LOG.log(Level.INFO, input.readLine());

        LOG.info("Message -----------------------------");
        output.print(SmtpProtocol.FROM + email.getFrom() + SmtpProtocol.RETURN);
        output.flush();
        LOG.log(Level.CONFIG,"to");
        for (String to : email.getTo()) {
            output.print(SmtpProtocol.TO + to + SmtpProtocol.RETURN);
            output.flush();
        }
        LOG.log(Level.CONFIG, "cc");
        for (String cc : email.getCc()) {
            output.print(SmtpProtocol.CC + cc + SmtpProtocol.RETURN);
            output.flush();
        }

        output.print(SmtpProtocol.ENCODAGE_UTF8 + SmtpProtocol.RETURN);
        output.flush();

        LOG.log(Level.CONFIG,"body");
        output.print(email.getMessage());
        output.flush();
    
        LOG.log(Level.CONFIG,"end");
        output.print(SmtpProtocol.END_MESSAGE);
        output.flush();
        LOG.log(Level.INFO, input.readLine());

        LOG.info("Quit -----------------------------------");
        output.print(SmtpProtocol.CMD_QUIT + SmtpProtocol.RETURN);
        output.flush();
        LOG.log(Level.INFO, input.readLine());
    }
}
