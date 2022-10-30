package ua.com.foxminded.model;

public class Student {
    private String name;
    private String surname;
    private int group;
    private int id;

    public Student(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public int getGroup() {

        return group;
    }

    public String getName(){

        return name;
    }

    public String getSurname(){

        return surname;
    }

    public void setGroup(int groupId) {

        this.group = groupId;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student))
            return false;
        Student other = (Student) o;
        boolean valueEquals = (this.name == null && other.name == null)
            || (this.name != null && this.name.equals(other.name));
        boolean storeEquals = (this.surname == null && other.surname == null)
            || (this.surname != null && this.surname.equals(other.surname));
        return valueEquals && storeEquals;
    }

    @Override
    public final int hashCode(){
        int hashCode = 17;
        if(name != null) {
            hashCode = 31 * hashCode + name.hashCode();
        }
        if (surname != null) {
            hashCode = 31 * hashCode * surname.hashCode();
        }
        return hashCode;
    }
}
