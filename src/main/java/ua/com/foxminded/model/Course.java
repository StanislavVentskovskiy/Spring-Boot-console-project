package ua.com.foxminded.model;

public class Course {
    private String name;
    private String courseDescription;

    public Course(String name, String courseDescription){
        this.name = name;
        this.courseDescription = courseDescription;
    }

    public String getName(){

        return name;
    }

    public String getCourseDescription(){

        return courseDescription;
    }
}
