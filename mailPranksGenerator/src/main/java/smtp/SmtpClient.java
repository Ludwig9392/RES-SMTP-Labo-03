package smtp;

import config.ConfigManager;
import model.mail.Mail;

import java.io.*;
import java.net.Socket;

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

        System.out.println(input.readLine());
        System.out.println("Test EHLO");
        output.print("EHLO Moto \r\n"); // Beware: do not use println() --> not recognized by server
        output.flush();

        String str;
        while((str = input.readLine()).substring(0,4) != "250 ") {
            System.out.println(str);
        }

    }
}
