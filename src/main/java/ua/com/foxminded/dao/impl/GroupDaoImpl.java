package ua.com.foxminded.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.dao.GroupDao;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.dao.repository.GroupRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    GroupRepository groupRepository;

    private static final Logger LOG = LoggerFactory.getLogger(GroupDaoImpl.class);

    public void addGroupList(ArrayList<Group> groupsList){
        LOG.info("Enter method insertGroupList()");
        groupsList.forEach((group) -> entityManager.persist(group));
        LOG.info("Leave method insertGroupList()");
    }

    @Override
    public void addGroup(final Group group) {
       LOG.info("enter method insertGroup()");
       entityManager.persist(group);
       LOG.info("Leave group insertGroup()");
    }
}
