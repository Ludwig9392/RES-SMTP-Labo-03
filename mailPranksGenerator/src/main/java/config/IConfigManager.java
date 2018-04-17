package config;

import model.mail.Group;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public interface IConfigManager {

    public String getSmtpServerAdresse();
    public int getSmtpServerListenPort();
    public int getNumberOfGroups();
    public Group getWitnesses();
    public Group getVictims();
    public List<String> getMessages();

}
