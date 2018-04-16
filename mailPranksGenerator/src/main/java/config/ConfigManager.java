package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * A class that managed all the configurations files infos and use them for this
 * Project. (Parse config info in order to apply them in this Java Project).
 *
 * @author Frueh Loïc
 */

public class ConfigManager implements IConfigManager {

    private Properties properties;

    public ConfigManager() throws IOException {
        properties = getProperties("../config/config.properties");
    }

    public Properties getProperties(String fileName) throws IOException {
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
}
