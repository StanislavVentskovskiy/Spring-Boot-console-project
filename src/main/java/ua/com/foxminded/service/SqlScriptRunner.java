package ua.com.foxminded.service;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Properties;
import org.apache.ibatis.jdbc.ScriptRunner;
import ua.com.foxminded.exceptions.DAOException;
import ua.com.foxminded.util.ApplicationProperties;
import ua.com.foxminded.util.ConnectionFactory;

public class SqlScriptRunner {
    private String schema;
    private String schemaPropertiesName = "postgres.schema";
    private Properties properties;
    private ApplicationProperties applicationProperties = new ApplicationProperties();


    public void runScript() {
        try(Connection connection = ConnectionFactory.getInstance().makeConnection()) {
            connection.setAutoCommit(false);
            properties = applicationProperties.getProperties();
            schema = properties.getProperty(schemaPropertiesName);
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            InputStreamReader scriptReader = new InputStreamReader(new FileInputStream(schema));
            scriptRunner.runScript(scriptReader);
            scriptReader.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }
}
