package ua.com.foxminded.dao;

import ua.com.foxminded.model.Group;
import java.util.ArrayList;

public interface GroupDao {

    int insertGroup(Group group);

    void insertGroupList(ArrayList<Group> groupsList);
}
