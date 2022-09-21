package ua.com.foxminded.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
    private static Properties properties = new Properties();

    public static Properties getProperties() {

        return properties;
    }

    static {
        properties = new Properties();
        try {
            InputStream in = ConnectionFactory.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(in);
        } catch (Exception e) {
            System.out.println("Configuration error");
        }
    }
}
