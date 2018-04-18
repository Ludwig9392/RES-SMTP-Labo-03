package model.prank;

import config.IConfigManager;
import model.mail.Group;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class that modelize a Generator of forged-email.
 *
 * @author Frueh Loïc
 */
public class PrankGenerator {
    private IConfigManager config;
    private final static int MIN_NUMBER_PERSON_PER_GROUP = 3;

    public PrankGenerator(IConfigManager config) {
        this.config = config;
    }

    public List<Group> createPrankGroups(Group victims, int numberOfGroups) {
        List<Group> groupList = new ArrayList<Group>();
        for (int i = 0; i < numberOfGroups; ++i) {
            groupList.add(new Group());
        }

        victims.shuffle();

        int groupNumber = 0;
        while (victims.getNumberOfPerson() > 0) {
            groupList.get(groupNumber).addPerson(victims.removePerson(0));
            groupNumber = (groupNumber + 1) % numberOfGroups;
        }

        return groupList;
    }

    public List<Prank> generatePranks() {
        List<Prank> pranks = new ArrayList<Prank>();

        int numberOfGroups = config.getNumberOfGroups();
        int numberOfvictims = config.getVictims().getNumberOfPerson();

        if (numberOfvictims / numberOfGroups < MIN_NUMBER_PERSON_PER_GROUP){
            numberOfGroups = numberOfvictims / 3;
            System.out.println("Sorry but with your configuration we can only generate " + numberOfGroups + "groups maximum, edit your configuration files in consequence.");
            return pranks; // Empty list
        }

        List<Group> groupsToPrank = createPrankGroups(config.getVictims(), numberOfGroups);

        List<String> messages = config.getMessages();
        Collections.shuffle(messages);
        int messageIndex = 0;
        for (Group group : groupsToPrank) {
            group.shuffle();

            Prank prank = new Prank();
            prank.setFakeSender(group.removePerson(0));
            prank.setReceivers(group);

            Group cc = config.getWitnesses();
            prank.setWitnesses(cc);

            prank.setMessage(messages.get(messageIndex));
            messageIndex = (messageIndex + 1) % messages.size();

            pranks.add(prank);
        }

        return pranks;
    }
}
