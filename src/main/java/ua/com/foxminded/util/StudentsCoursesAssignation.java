package ua.com.foxminded.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class StudentsCoursesAssignation {
   private ArrayList<Integer> studentsIdList = new ArrayList<>();
   private ArrayList<Integer> coursesIdList = new ArrayList<>();

   @Autowired
   private CoursesStudentsDaoImpl coursesStudentsDao;

    public void setStudentsIdList(ArrayList<Integer> studentsIdList) {
        this.studentsIdList = studentsIdList;
    }

    public void setCoursesIdList(ArrayList<Integer> coursesIdList) {
        this.coursesIdList = coursesIdList;
    }

    public void assignCoursesToStudent() {
        int courseNumber;
        for (int index = studentsIdList.size(); index > 0; index--) {
            courseNumber = generateCoursesNumber();
            insertOneStudentAndFewCourses(index, courseNumber);
        }
    }

     private int generateCoursesNumber () {
            int coursesNumber = (int) (Math.random() * (4 - 1)) + 1;

            return coursesNumber;
        }

     private void insertOneStudentAndFewCourses (int studentId, int courseNumber){
           Collections.shuffle(coursesIdList);
            for (int index = courseNumber; index > 0; index--) {
                coursesStudentsDao.insertStudentAndCourse(studentId, coursesIdList.get(index));
            }
        }
    }
