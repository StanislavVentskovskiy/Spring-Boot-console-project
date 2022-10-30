package ua.com.foxminded.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.model.Group;
import java.util.ArrayList;

@Component
public class GroupDaoImpl implements GroupDao {

   @Autowired
   private JdbcTemplate jdbcTemplate;
   private final String insertGroupSQL = "INSERT INTO postgres.schoolconsoleapp.groups(group_name) " + "VALUES(?)";

    @Override
    public int insertGroup(final Group group) {
       return jdbcTemplate.update(insertGroupSQL, group.getName());
    }

    public void insertGroupList(ArrayList<Group> groupsList){
        groupsList.forEach((group) -> insertGroup(group));
    }
}
