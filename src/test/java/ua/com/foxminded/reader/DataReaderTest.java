package ua.com.foxminded.reader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataReader.class)
public class DataReaderTest {
    private HashMap<String,String> expectedData = new HashMap<>();
    private HashMap<String,String> actualData = new HashMap<>();

    @Value("${test.data.directory}")
    private String testDataPath;

    @Autowired
    DataReader dataReader;

    @Before
    public void initExpectedData(){
        expectedData.put("test","test");
    }

    @Test
    public void testReadGeneratedDataInputTestData_shouldReturnExpectedData() throws IOException {
        actualData = dataReader.readGeneratedData(Paths.get(testDataPath));
        assertTrue(expectedData.equals(actualData));
    }
}
