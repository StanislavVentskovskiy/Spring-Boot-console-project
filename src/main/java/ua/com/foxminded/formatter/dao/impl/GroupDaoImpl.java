package ua.com.foxminded.formatter.dao.impl;

import ua.com.foxminded.formatter.dao.GroupDao;
import ua.com.foxminded.formatter.dao.DAOException;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class GroupDaoImpl implements GroupDao {
    private final Connection connection = ConnectionFactory.getInstance().makeConnection();
    private final String SQL = "INSERT INTO postgres.schoolconsoleapp.groups(group_name) " + "VALUES(?)";

    @Override
    public void insertGroup(Group group) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
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
