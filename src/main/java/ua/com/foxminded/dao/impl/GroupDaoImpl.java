package ua.com.foxminded.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.model.Group;
import java.util.ArrayList;
import static ua.com.foxminded.controller.LoggerController.LOG;

@Component
public class GroupDaoImpl implements GroupDao {

   @Autowired
   private JdbcTemplate jdbcTemplate;

   private final String insertGroupSQL = "INSERT INTO postgres.schoolconsoleapp.groups(group_name) " + "VALUES(?)";

    @Override
    public int insertGroup(final Group group) {
       LOG.debug("Sql statement: " + insertGroupSQL + " " + group.getName());
       return jdbcTemplate.update(insertGroupSQL, group.getName());
    }

    public void insertGroupList(ArrayList<Group> groupsList){
        LOG.info("Enter method insertGroupList()");
        groupsList.forEach((group) -> insertGroup(group));
        LOG.info("Leave method insertGroupList()");
    }
}
