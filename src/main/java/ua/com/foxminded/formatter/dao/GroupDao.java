package ua.com.foxminded.formatter.dao;

import ua.com.foxminded.model.Group;
import java.util.ArrayList;

public interface GroupDao {
    void insertGroup(Group group);

    void insertGroupList(ArrayList<Group> groupsList);
}
