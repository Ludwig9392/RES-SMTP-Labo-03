package config;

import model.mail.Group;
import model.mail.Person;
import smtp.SmtpProtocol;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * A class that managed all the configurations files infos and use them for this
 * Project. (Parse config info in order to apply them in this Java Project).
 *
 * @author Frueh Loïc
 */

public class ConfigManager implements IConfigManager {

    private Properties properties;
    private Group victims;
    private List<String> messages;


    public ConfigManager() throws IOException {
        properties = getProperties("./config/config.properties");
        victims = loadVictimsFile("./config/victims");
        messages = loadMessagesFile("./config/messages");
    }

    private Properties getProperties(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(file);
        return properties;
    }

    public String getSmtpServerAdresse() {
        return properties.getProperty("smtpServer");
    }

    public int getSmtpServerListenPort() {
        return Integer.parseInt(properties.getProperty("smtpListenPort"));
    }

    public int getNumberOfGroups() {
        return Integer.parseInt(properties.getProperty("numberOfGroups"));
    }

    public Group getWitnesses() {
        String witnessesInLine = properties.getProperty("witnesses");
        String[] witnessesParsed = witnessesInLine.split(",");
        Group witnesses = new Group();
        for (String witnessAddress : witnessesParsed){
            witnesses.addPerson(new Person(witnessAddress));
        }

        return witnesses;
    }

    private Group loadVictimsFile(String fileName) throws IOException {
        Group victims = new Group();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

        String address;
        while ((address = reader.readLine()) != null) {
            victims.addPerson(new Person(address));
        }

        return victims;

    }

    private List<String> loadMessagesFile(String fileName) throws IOException {
        List<String> messages = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

        String line;
        while ((line = reader.readLine()) != null) {
            StringBuilder message = new StringBuilder();
            while (line != null && !line.equals("***")){
                message.append(line);
                message.append(SmtpProtocol.RETURN);
                line = reader.readLine();
            }
            messages.add(message.toString());
        }
        return messages;
    }

    public Group getVictims() {
        Group victims = new Group();
        victims.addMultiplePersons(this.victims.getGroup());
        return victims;
    }

    public List<String> getMessages() {
        List<String> messages = new ArrayList<String>();
        messages.addAll(this.messages);
        return messages;
    }
}
