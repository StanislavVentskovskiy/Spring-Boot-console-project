package ua.com.foxminded.util;

import org.springframework.stereotype.Service;
import ua.com.foxminded.model.Group;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

@Service
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
        Random randomIndex = new Random();
        StringBuilder characterPart = new StringBuilder();
        Stream<Integer> stream = Stream.of(1,2);
        stream.forEach(part -> characterPart.append(possibleCharacterInGroupName.charAt(randomIndex.nextInt(27-1))));

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
