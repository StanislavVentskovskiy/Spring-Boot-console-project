package ua.com.foxminded.reader;

import ua.com.foxminded.reader.DataReader;
import ua.com.foxminded.util.PathPropertiesUtil;
import java.nio.file.Paths;
import java.util.HashMap;

public class CoursesDataReader {
    private  HashMap<String,String> coursesNameAndDescription = new HashMap<>();

    public CoursesDataReader() {
        DataReader dataReader = new DataReader();
        coursesNameAndDescription = (HashMap<String, String>) dataReader.readGeneratedData(Paths.get(PathPropertiesUtil.get("courses.name.and.description.directory")));
    }

    public HashMap<String,String> getCoursesNameAndDescription(){

        return coursesNameAndDescription;
    }
}
