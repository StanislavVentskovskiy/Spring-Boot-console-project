package ua.com.foxminded.model;

import javax.persistence.*;

@Entity
@Table(name="students",schema = "schoolconsoleapp")
public class Student {

    @Column(name="first_name")
    private String name;

    @Column(name="last_name")
    private String surname;

    @Column(name="group_id", nullable = true)
    private Integer group;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    public Student(){}

    public Student(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getGroup() {
        if (group == null) {
            return 0;
        } else {
            return group;
        }
    }

    public void setGroup(int group) {
        this.group = group;
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
