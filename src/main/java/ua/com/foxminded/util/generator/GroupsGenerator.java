package ua.com.foxminded.util.generator;

import org.springframework.stereotype.Service;
import ua.com.foxminded.model.Group;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;
import static ua.com.foxminded.controller.LoggerController.LOG;

@Service
public class GroupsGenerator {
    private ArrayList<Group> groupList  = new ArrayList<>();
    private String possibleCharacterInGroupName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int groupNumber;

    public ArrayList<Group> generateGroups() {
        LOG.info("Enter method generateGroups()");
        for(int index = 1; index <= 10; index++){
            Group group = new Group(generateGroupName(generateCharacterPart(),generateIntegerPart()));
            groupList.add(group);
            LOG.info("Generated group list");
        }
        LOG.info("Leave method generateGroups()");
        return groupList;
    }

    private String generateCharacterPart() {
        LOG.info("Entered method generateCharacterPart()");
        Random randomIndex = new Random();
        StringBuilder characterPart = new StringBuilder();
        Stream<Integer> stream = Stream.of(1,2);
        stream.forEach(part -> characterPart.append(possibleCharacterInGroupName.charAt(randomIndex.nextInt(27-1))));
        LOG.info("Generated character part of group name");
        LOG.info("Leave method generateCharacterPart()");

        return characterPart.toString();
    }

    private String generateIntegerPart() {
        LOG.info("Enter method generateIntegerPart()");
        Random random = new Random();
        groupNumber = random.nextInt(100-10)+10;
        LOG.info("Generated integer part of group name");
        LOG.info("Leave method generateIntegerPart()");

        return String.valueOf(groupNumber);
    }

    private String generateGroupName(String charPart, String intPart) {
        LOG.info("Entered method generateGroupName()");
        String fullGroupName = charPart + "-" + intPart;
        LOG.info("Prepared full name of group");
        LOG.info("Leave method generateGroupName()");

        return fullGroupName;
    }
}
