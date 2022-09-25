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
}
