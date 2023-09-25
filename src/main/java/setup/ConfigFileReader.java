package setup;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {

    // Load properties from application.properties file in the classpath
    public Properties readProperties() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src//main//resources//application.properties"));
            return properties;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}