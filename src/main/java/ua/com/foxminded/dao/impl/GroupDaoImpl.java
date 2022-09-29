package ua.com.foxminded.dao.impl;

import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.exceptions.DAOException;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class GroupDaoImpl implements GroupDao {
    private final String insertGroupSQL = "INSERT INTO postgres.schoolconsoleapp.groups(group_name) " + "VALUES(?)";

    @Override
    public void insertGroup(final Group group) {
        try(Connection connection = ConnectionFactory.getInstance().makeConnection();
        PreparedStatement statement = connection.prepareStatement(insertGroupSQL)) {
            statement.setString(1, group.getName());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    public void insertGroupList(ArrayList<Group> groupsList){
        groupsList.forEach((group) -> insertGroup(group));
    }
}
