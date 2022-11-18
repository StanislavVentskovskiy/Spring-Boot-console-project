package ua.com.foxminded.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.dao.repository.GroupRepository;
import java.util.ArrayList;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    @Autowired
    GroupRepository groupRepository;

    private static final Logger LOG = LoggerFactory.getLogger(GroupDaoImpl.class);

    @Override
    public Group addGroup(Group group) {
        LOG.info("Enter method addGroup()");
        return groupRepository.save(group);
    }

    public void deleteGroup(int groupId){
        LOG.info("Enter method deleteGroup()");
        groupRepository.deleteById(groupId);
        LOG.info("Leave method deleteGroup()");
    }

    public void addGroupList(ArrayList<Group> groupsList){
        LOG.info("Enter method insertGroupList()");
        groupRepository.saveAll(groupsList);
        LOG.info("Leave method insertGroupList()");
    }

    public Group updateGroup(int groupId, String groupName){
        LOG.info("Enter method updateGroup()");
        Group group = groupRepository.findById(groupId).orElse(null);
        group.setName(groupName);
        LOG.info("Leave method updateGroup()");

        return groupRepository.save(group);
    }
}
