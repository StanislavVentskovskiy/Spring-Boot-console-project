package ua.com.foxminded.util;

import ua.com.foxminded.model.Group;
import java.util.ArrayList;
import java.util.Random;

public class GroupsGenerator {
    private ArrayList<Group> groupList  = new ArrayList<>();
    private String possibleCharacterInGroupName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int groupNumber;

    public ArrayList<Group> generateGroups() {
        for(int index = 1; index <= 10; index++){
            Group group = new Group(generateGroupName(generateCharacterPart(),generateIntegerPart()));
            groupList.add(group);
        }

        return groupList;
    }

    private String generateCharacterPart() {
        int groupIndex;
        Random randomIndex = new Random();
        StringBuilder characterPart = new StringBuilder();
        while (characterPart.length() < 2) {
            groupIndex = randomIndex.nextInt(27-1);
            characterPart.append(possibleCharacterInGroupName.charAt(groupIndex));
        }

        return characterPart.toString();
    }

    private String generateIntegerPart() {
        Random random = new Random();
        groupNumber = random.nextInt(100-10)+10;

        return String.valueOf(groupNumber);
    }

    private String generateGroupName(String charPart, String intPart) {
        String fullGroupName = charPart + "-" + intPart;

        return fullGroupName;
    }
}
