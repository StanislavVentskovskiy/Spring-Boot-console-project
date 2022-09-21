package ua.com.foxminded.service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Properties;
import org.apache.ibatis.jdbc.ScriptRunner;
import ua.com.foxminded.util.ApplicationProperties;
import ua.com.foxminded.util.ConnectionFactory;

public class SqlScriptRunner {
    private Connection connection = null;
    private String schema;
    private Properties properties;

    public void runScript() {
        try {
            connection = ConnectionFactory.getInstance().makeConnection();
            connection.setAutoCommit(false);
            ApplicationProperties applicationProperties = new ApplicationProperties();
            properties = applicationProperties.getProperties();
            schema = properties.getProperty("postgres.schema");
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            InputStreamReader scriptReader = new InputStreamReader(new FileInputStream(schema));
            scriptRunner.runScript(scriptReader);
            scriptReader.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
