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
    private int serverListenPort;
    private String serverAdresse;

    private BufferedReader input;
    private PrintWriter output;

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

        // Second implementation test for the partial connection with the SMTP Server

        System.out.println(input.readLine());
        System.out.println("Test EHLO ------------------------");
        output.print("EHLO Moto \r\n"); // Beware: do not use println() --> not recognized by server
        output.flush();

        String serveurResponse;
        while(!Objects.equals((serveurResponse = input.readLine()).substring(0,4), "250 ")) {
            System.out.println(serveurResponse);
        }
        System.out.println(serveurResponse);
        System.out.println();

        System.out.println("MAIL FROM ------------------------");
        output.print("MAIL FROM: " + email.getFrom() + "\r\n");
        output.flush();
        System.out.println(input.readLine());
        System.out.println();

        System.out.println("RCPT TO --------------------------");
        output.print("RCPT TO: " + email.getTo() + "\r\n");
        output.flush();
        System.out.println(input.readLine());
        System.out.println();

        System.out.println("DATA -----------------------------");
        output.print("DATA \r\n");
        output.flush();
        System.out.println(input.readLine());
        System.out.println();
    }
}
