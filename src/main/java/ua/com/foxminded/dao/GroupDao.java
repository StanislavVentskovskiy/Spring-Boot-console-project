package ua.com.foxminded.dao;

import ua.com.foxminded.model.Group;
import java.util.ArrayList;

public interface GroupDao {

    void addGroup(final Group group);

    void addGroupList(ArrayList<Group> groupsList);
}
