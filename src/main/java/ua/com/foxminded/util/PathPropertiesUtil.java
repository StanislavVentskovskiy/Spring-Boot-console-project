package ua.com.foxminded.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public final class PathPropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key) {

        return PROPERTIES.getProperty(key);
    }

    private PathPropertiesUtil() {
    }

    private static void loadProperties() {
        try (InputStream inputStream = PathPropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
