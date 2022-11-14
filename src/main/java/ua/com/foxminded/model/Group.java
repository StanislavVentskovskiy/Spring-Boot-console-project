package ua.com.foxminded.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="groups",schema = "schoolconsoleapp")
public class Group implements Serializable {

    @Column(name = "group_name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int groupId;

    public Group(String name){
        this.name = name;
    }

    public Group(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Group))
            return false;
        Group other = (Group) o;
        boolean valueEquals = this.name != null && this.name.equals(other.name);
        return valueEquals;
    }

    @Override
    public final int hashCode(){
        int hashCode = 17;
        if(name != null) {
            hashCode = 31 * hashCode + name.hashCode();
        }
        return hashCode;
    }
}
