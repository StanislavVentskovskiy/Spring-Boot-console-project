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
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    public void addGroupList(ArrayList<Group> groupsList){
        LOG.info("Enter method insertGroupList()");
        groupRepository.saveAll(groupsList);
        LOG.info("Leave method insertGroupList()");
    }
}
