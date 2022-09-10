package ua.com.foxminded.scriptrunner;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.ibatis.jdbc.ScriptRunner;

public class SqlScriptRunner {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "4321";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void runScript() {

        try {
            Connection connection = connect();
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            InputStreamReader readScript = new InputStreamReader(new FileInputStream("C:\\Users\\mrsti\\AppData\\Roaming\\JetBrains\\IntelliJIdea2022.2\\consoles\\db\\8fe8ebd2-746b-4e20-a8dd-e7adbc83c018\\console.sql"));
            scriptRunner.runScript(readScript);
            readScript.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
