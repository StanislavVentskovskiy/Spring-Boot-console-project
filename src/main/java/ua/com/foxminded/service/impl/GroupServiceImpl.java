package ua.com.foxminded.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.dao.impl.GroupDaoImpl;
import ua.com.foxminded.model.Group;

public class GroupServiceImpl {

    @Autowired
    GroupDaoImpl groupDaoImpl;

    public void deleteGroup(int id){
        groupDaoImpl.deleteGroup(id);
    }

    public Group addNewGroup(String groupName){
        Group group = new Group(groupName);
        return groupDaoImpl.addGroup(group);
    }

    public Group updateGroup(int groupId, String groupName){
        return groupDaoImpl.updateGroup(groupId, groupName);
    }

}
