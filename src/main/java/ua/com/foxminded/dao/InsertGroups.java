package ua.com.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class InsertGroups {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "4321";

    public Connection connect() throws SQLException {

        return DriverManager.getConnection(url,user,password);
    }

    public void insertGroups(HashMap<Integer,String> groupsList) {
        String SQL = "INSERT INTO postgres.schoolconsoleapp.groups(id, group_name) " + "VALUES(?,?)";

        try(
            Connection connection = connect();
            PreparedStatement statement = connection.prepareStatement(SQL);) {
                for (Map.Entry<Integer,String> entry : groupsList.entrySet()) {
                    statement.setInt(1, entry.getKey());
                    statement.setString(2,entry.getValue());
                    statement.executeUpdate();
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
