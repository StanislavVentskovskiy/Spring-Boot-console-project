package ua.com.foxminded.datagenerator;

import java.util.HashMap;
import java.util.Random;

public class GroupGenerator {
    public HashMap<Integer,String> groupIdNameLink = new HashMap<>();
    private String possibleCharacterInGroupName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int groupNumber;

    public HashMap<Integer,String> getGroupIdNameLink(){

        return groupIdNameLink;
    }

    public String generateCharacterPart() {
        int groupIndex;
        Random randomIndex = new Random();
        StringBuilder characterPart = new StringBuilder();
        while (characterPart.length() < 2) {
            groupIndex = randomIndex.nextInt(27-1);
            characterPart.append(possibleCharacterInGroupName.charAt(groupIndex));
        }

        return characterPart.toString();
    }

    public String generateIntegerPart() {
        groupNumber = (int) (Math.random()*(100-10))+10;
        return String.valueOf(groupNumber);
    }

    private String generateGroupName(String charPart, String intPart) {
        String fullGroupName = charPart + "-" + intPart;

        return fullGroupName;
    }

    public void generateTenGroups() {
        for(int index = 1; index <= 10; index++){
            groupIdNameLink.put(index,generateGroupName(generateCharacterPart(),generateIntegerPart()));
        }
    }
}
