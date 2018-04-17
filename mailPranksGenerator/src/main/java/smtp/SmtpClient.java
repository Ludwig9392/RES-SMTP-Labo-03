package smtp;

import config.ConfigManager;
import model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * A SMTP client that can send e-mails through a SMTP server.
 *
 * @author Frueh Loïc
 */
public class SmtpClient implements ISmtpClient {

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

    public SmtpClient(ConfigManager config) {
        this.serverAdresse = config.getSmtpServerAdresse();
        this.serverListenPort = config.getSmtpServerListenPort();
    }

    public void sendEmail(Mail email) throws IOException {
        socket = new Socket(serverAdresse, serverListenPort);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        // Test of implementation for a full connection with the SMTP Server (with multiple receiver and CCs)

        System.out.println(input.readLine());
        System.out.println("Test EHLO ------------------------");
        output.print(SmtpProtocol.CMD_HELLO + " Moto" + SmtpProtocol.RETURN); // Beware: do not use println() --> not recognized by server
        output.flush();

        String serveurResponse;
        while(!Objects.equals((serveurResponse = input.readLine()).substring(0,4), "250 ")) {
            System.out.println(serveurResponse);
        }
        System.out.println(serveurResponse);
        System.out.println();

        System.out.println("MAIL FROM ------------------------");
        output.print(SmtpProtocol.CMD_MAIL + email.getFrom() + SmtpProtocol.RETURN);
        output.flush();
        System.out.println(input.readLine());
        System.out.println();

        System.out.println("RCPT TO --------------------------");
        for (String receiver : email.getTo()) {
            output.print(SmtpProtocol.CMD_RCPT + receiver + SmtpProtocol.RETURN);
            output.flush();
            System.out.println(input.readLine());
            System.out.println();
        }

        for (String cc : email.getCc()) {
            output.print(SmtpProtocol.CMD_RCPT + cc + SmtpProtocol.RETURN);
            output.flush();
            System.out.println(input.readLine());
            System.out.println();
        }

        System.out.println("DATA -----------------------------");
        output.print(SmtpProtocol.CMD_DATA + SmtpProtocol.RETURN);
        output.flush();
        System.out.println(input.readLine());
        System.out.println();

        System.out.println("Message -----------------------------");
        output.print(SmtpProtocol.FROM + email.getFrom() + SmtpProtocol.RETURN);
        output.flush();
        System.out.println("to !!!!!!!");
        for (String to : email.getTo()) {
            output.print(SmtpProtocol.TO + to + SmtpProtocol.RETURN);
            output.flush();
        }
        System.out.println("cc !!!!!!!");
        for (String cc : email.getCc()) {
            output.print(SmtpProtocol.CC + cc + SmtpProtocol.RETURN);
            output.flush();
        }
        System.out.println("subject !!!!!!!");
        output.print(SmtpProtocol.SUBJECT + email.getSubject() + SmtpProtocol.RETURN);
        output.flush();
        output.print(SmtpProtocol.RETURN);
        output.flush();

        System.out.println("body !!!!!!!");
        output.print(email.getMessage());
        output.flush();

        System.out.println("end !!!!!!!");
        output.print(SmtpProtocol.END_MESSAGE);
        output.flush();
        System.out.println(input.readLine());
        System.out.println();

        System.out.println("Quit -----------------------------------");
        output.print(SmtpProtocol.CMD_QUIT + SmtpProtocol.RETURN);
        output.flush();
        System.out.println(input.readLine());
        System.out.println();
    }
}
