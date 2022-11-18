package ua.com.foxminded.service;

import ua.com.foxminded.model.Group;

public interface GroupService {

    void deleteGroup(int id);

    Group addNewGroup(String groupName);

    Group updateGroup(int groupId, String groupName);
}
