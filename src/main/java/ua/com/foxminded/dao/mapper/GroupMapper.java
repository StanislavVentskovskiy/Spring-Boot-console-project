package ua.com.foxminded.dao.mapper;

import ua.com.foxminded.model.Group;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper implements RowMapper<Group> {
    @Override
    public Group mapRow(ResultSet resultSet, int i) throws SQLException {
        Group group = new Group(resultSet.getString("group_name"));
        return group;
    }
}
