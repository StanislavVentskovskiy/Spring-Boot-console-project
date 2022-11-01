package ua.com.foxminded.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.foxminded.Main;
import ua.com.foxminded.model.Group;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:test-schema.sql"})
@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
public class GroupDaoImplTest {

    @MockBean
    private Main main;

    @Autowired
    private GroupDaoImpl groupDao;

    private int expected;
    private int actual;
    private Group testGroup;

    @Before
    public void initTestData(){
        expected = 1;
        testGroup = new Group("test");
    }

    @Test
    public void testInsertGroup_shouldReturnCorrectStatus(){
        actual = groupDao.insertGroup(testGroup);

        assertTrue(expected == actual);
    }
}
