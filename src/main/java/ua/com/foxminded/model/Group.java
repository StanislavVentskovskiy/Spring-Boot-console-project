package ua.com.foxminded.model;

public class Group {
    private String name;
    private int groupId;

    public Group(String name){

        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName(){

        return name;
    }
}
