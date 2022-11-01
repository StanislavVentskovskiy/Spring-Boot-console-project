package ua.com.foxminded.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.model.Group;
import ua.com.foxminded.util.generator.GroupsGenerator;
import java.util.ArrayList;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GroupsGenerator.class})
public class GroupsGeneratorTest {
    private ArrayList<Group> actualGroupList;

    @Autowired
    GroupsGenerator groupsGenerator;

    @Test
    public void generateGroups_shouldReturnGroupObject(){
       actualGroupList = groupsGenerator.generateGroups();

       assertTrue(actualGroupList.get(0) instanceof Group);
    }
}
