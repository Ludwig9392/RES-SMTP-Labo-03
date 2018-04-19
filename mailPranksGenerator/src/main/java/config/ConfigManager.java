package config;

import model.mail.*;
import smtp.SmtpProtocol;
import java.io.*;
import java.util.*;

/**
 * A class that managed all the configurations files infos and use them for this project.
 * (Parse config info in order to apply them in this Java Project).
 *
 * @author Frueh Loïc, Dejvid Muaremi
 */

public class ConfigManager implements IConfigManager {

    private Properties properties;
    private Group victims;
    private List<String> messages;
    
    
    /***
     * Default constructor for a manager.
     * You must have the config files under ./config when this is called.
     * The directory config contains :
     * config.properties which has the configurations.
     * victims which contains the list of victims email address.
     * messages which contains the prank message.
     *
     * @throws IOException when it can't read one of the config files.
     */
    public ConfigManager() throws IOException {
        properties = getProperties("../mailPranksGenerator/config/config.properties");
        victims = loadVictimsFile("../mailPranksGenerator/config/victims");
        messages = loadMessagesFile("../mailPranksGenerator/config/messages");
    }
    
    /***
     * Parse a property file and take the properties of it.
     * This file contains the configuration for the server, port, number of groups, and the witnesses.
     * @param fileName the path and the name of the file.
     * @return the properties in a Java Properties object.
     * @throws IOException when it can't read one of the properties files.
     */
    private Properties getProperties(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        Properties properties = new Properties();
        properties.load(file);
        return properties;
    }
    
    /**
     * Get the address of the smtp server.
     * @return the address of the server in a string.
     */
    public String getSmtpServerAddress() {
        return properties.getProperty("smtpServer");
    }
    
    /***
     * Get the listening port of the smtp server.
     * @return the listening port.
     */
    public int getSmtpServerListenPort() {
        return Integer.parseInt(properties.getProperty("smtpListenPort"));
    }
    
    /***
     * Get the number of groups you want to create for the pranks.
     * @return a number of groups.
     */
    public int getNumberOfGroups() {
        return Integer.parseInt(properties.getProperty("numberOfGroups"));
    }
    
    /***
     * The witnesses are the peoples who will receive the pranks to check if the work correctly.
     * @return a Group of witnesses.
     */
    public Group getWitnesses() {
        String witnessesInLine = properties.getProperty("witnesses");
        String[] witnessesParsed = witnessesInLine.split(",");
        Group witnesses = new Group();
        for (String witnessAddress : witnessesParsed){
            witnesses.addPerson(new Person(witnessAddress));
        }

        return witnesses;
    }
    
    /***
     * Parse a file and add the list of victims to the Group of victims
     * @param fileName the path and the name of the file.
     * @return the group of victims in a Group.
     * @throws IOException when it can't read one of the properties files.
     */
    private Group loadVictimsFile(String fileName) throws IOException {
        Group victims = new Group();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

        String address;
        while ((address = reader.readLine()) != null) {
            victims.addPerson(new Person(address));
        }

        return victims;

    }
    
    /***
     * Parse the message file and return a list of message.
     * @param fileName the path and the name of the file.
     * @return a list of message
     * @throws IOException when it can't read one of the properties files.
     */
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
    
    /***
     * Get a Group of victims
     * @return all the victims in a Group.
     */
    public Group getVictims() {
        Group victims = new Group();
        victims.addMultiplePersons(this.victims.getGroup());
        return victims;
    }
    
    /***
     * Get a list of message
     * @return the list of message
     */
    public List<String> getMessages() {
        List<String> messages = new ArrayList<String>();
        messages.addAll(this.messages);
        return messages;
    }
}
