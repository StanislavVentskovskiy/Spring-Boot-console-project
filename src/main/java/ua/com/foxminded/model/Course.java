package ua.com.foxminded.model;

public class Course {
    private String name;
    private String courseDescription;
    private int courseId;

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

    public int getCourseId() {

        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Course))
            return false;
        Course other = (Course) o;
        boolean valueEquals = (this.name == null && other.name == null)
            || (this.name != null && this.name.equals(other.name));
        boolean storeEquals = (this.courseDescription == null && other.courseDescription == null)
            || (this.courseDescription != null && this.courseDescription.equals(other.courseDescription));
        return valueEquals && storeEquals;
    }

    @Override
    public final int hashCode(){
        int hashCode = 17;
        if(name != null) {
            hashCode = 31 * hashCode + name.hashCode();
        }
        if (courseDescription != null) {
            hashCode = 31 * hashCode * courseDescription.hashCode();
        }
        return hashCode;
    }
}
