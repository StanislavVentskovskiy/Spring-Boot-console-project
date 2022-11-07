package ua.com.foxminded.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class StudentsCoursesAssignation {
   private ArrayList<Integer> studentsIdList = new ArrayList<>();
   private ArrayList<Integer> coursesIdList = new ArrayList<>();
   private static final Logger LOG = LoggerFactory.getLogger(StudentsCoursesAssignation.class);

   @Autowired
   private CoursesStudentsDaoImpl coursesStudentsDao;

    public void setStudentsIdList(ArrayList<Integer> studentsIdList) {
        this.studentsIdList = studentsIdList;
    }

    public void setCoursesIdList(ArrayList<Integer> coursesIdList) {
        this.coursesIdList = coursesIdList;
    }

    public void assignCoursesToStudent() {
        LOG.info("Enter method assignCoursesToStudent()");
        int courseNumber;
        for (int index = studentsIdList.size(); index > 0; index--) {
            courseNumber = generateCoursesNumber();
            insertOneStudentAndFewCourses(index, courseNumber);
            LOG.info("Updated information about courses and assigned students");
        }
        LOG.info("Leave method Enter method assignCoursesToStudent()");
    }

     private int generateCoursesNumber() {
            LOG.info("Enter method generateCoursesNumber()");
            int coursesNumber = (int) (Math.random() * (4 - 1)) + 1;
            LOG.info("Generated number of courses for student");
            LOG.info("Leave method generateCoursesNumber()");

            return coursesNumber;
        }

     private void insertOneStudentAndFewCourses(int studentId, int courseNumber){
        LOG.info("Enter method insertOneStudentAndFewCourses()");
        Collections.shuffle(coursesIdList);
        LOG.info("Shuffled list of course ID");
            for (int index = courseNumber; index > 0; index--) {
                coursesStudentsDao.insertStudentAndCourse(studentId, coursesIdList.get(index));
                LOG.info("Courses Id added to student");
        }
        LOG.info("Leave method insertOneStudentAndFewCourses()");
    }
}
