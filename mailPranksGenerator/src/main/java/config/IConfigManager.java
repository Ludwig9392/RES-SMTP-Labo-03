package config;

import java.io.IOException;
import java.util.Properties;

public interface IConfigManager {

    public Properties getProperties(String fileName) throws IOException;
    public String getSmtpServerAdresse();
    public int getSmtpServerListenPort();

}
