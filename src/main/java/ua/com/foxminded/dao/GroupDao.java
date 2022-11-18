package ua.com.foxminded.dao;

import ua.com.foxminded.model.Group;
import java.util.ArrayList;

public interface GroupDao {

    Group addGroup(final Group group);

    void deleteGroup(int groupId);

    void addGroupList(ArrayList<Group> groupsList);

    Group updateGroup(int groupId, String groupName);
}
