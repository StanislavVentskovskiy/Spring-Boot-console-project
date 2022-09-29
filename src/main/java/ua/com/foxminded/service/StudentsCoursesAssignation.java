package ua.com.foxminded.service;

import ua.com.foxminded.dao.impl.CoursesStudentsDaoImpl;
import java.util.ArrayList;
import java.util.Collections;

public class StudentsCoursesAssignation {
    private ArrayList<Integer> studentsIdList = new ArrayList<>();
    private ArrayList<Integer> coursesIdList = new ArrayList<>();
    private CoursesStudentsDaoImpl coursesStudentsDao;

    public StudentsCoursesAssignation(ArrayList<Integer> studentsIdList, ArrayList<Integer> coursesIdList){
        this.coursesIdList = coursesIdList;
        this.studentsIdList = studentsIdList;
        coursesStudentsDao = new CoursesStudentsDaoImpl();
    }

    public void assignCoursesToStudent() {
        int courseNumber;
        for(int index = studentsIdList.size(); index > 0; index--) {
            courseNumber = generateCoursesNumber();
            insertOneStudentAndFewCourses(index,courseNumber);
        }
    }

    private int generateCoursesNumber() {
        int coursesNumber = (int)(Math.random()*(4-1))+1;

        return coursesNumber;
    }

    private void insertOneStudentAndFewCourses(int studentId, int courseNumber) {
        Collections.shuffle(coursesIdList);
        for(int index = courseNumber; index > 0; index--) {
            coursesStudentsDao.insertStudentAndCourse(studentId,coursesIdList.get(index));
        }
    }
}
