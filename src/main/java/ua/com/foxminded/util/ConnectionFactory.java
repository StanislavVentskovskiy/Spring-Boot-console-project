package ua.com.foxminded.util;

import ua.com.foxminded.exceptions.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    private static String dburl;
    private static String user;
    private static String password;
    private static String postgresDriver;
    private static Properties properties;
    private static final ConnectionFactory factory = new ConnectionFactory();

    static {
        ApplicationProperties applicationProperties = new ApplicationProperties();
        properties = applicationProperties.getProperties();
        postgresDriver = properties.getProperty("postgresql.driver");
        dburl = properties.getProperty("dburl");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
    }

    public static ConnectionFactory getInstance(){

        return factory;
    }

    private ConnectionFactory(){};

    public Connection makeConnection() throws ConnectionException {
        Connection connection = null;
        try {
            Class.forName(postgresDriver);
            connection = DriverManager.getConnection(dburl,user,password);
        } catch (Exception e) {
            throw new ConnectionException(e);
        }

        return connection;
    }
}
