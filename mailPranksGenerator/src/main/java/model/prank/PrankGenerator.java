package model.prank;

import config.IConfigManager;
import model.mail.Group;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.*;

/**
 * A class that create a Generator of forged-email.
 *
 * @author Frueh Loïc, Dejvid Muaremi
 */
public class PrankGenerator {
    private IConfigManager config;
    private final static int MIN_NUMBER_PERSON_PER_GROUP = 3;
    private static final Logger LOG = Logger.getLogger(PrankGenerator.class.getName());
    
    /***
     * Constructor of the prank generator. We need an IConfigManager to set the configurations.
     * @param config an IConfigManager to set the configurations.
     */
    public PrankGenerator(IConfigManager config) {
        this.config = config;
    }
    
    /***
     * Create and return a List of Group who will get a prank.
     * @param victims the Group of victims we want to prank.
     * @param numberOfGroups the number of Groups we want to create.
     * @return a List of Group that contain the groups that will get a prank.
     */
    public List<Group> createPrankGroups(Group victims, int numberOfGroups) {
        List<Group> groupList = new ArrayList<Group>();
        LOG.info("BEGIN : Creation of the prank groups.");
        for (int i = 0; i < numberOfGroups; ++i) {
            groupList.add(new Group());
        }
        LOG.info("END : Creation of the prank groups.");
        victims.shuffle();

        int groupNumber = 0;
        LOG.info("BEGIN : Add victims to the prank groups.");
        while (victims.getNumberOfPerson() > 0) {
            groupList.get(groupNumber).addPerson(victims.removePerson(0));
            groupNumber = (groupNumber + 1) % numberOfGroups;
        }
        LOG.info("END : Add victims to the prank groups.");

        return groupList;
    }
    
    /***
     * Create a List of Prank that will be used to send messages to the victims in the config file as the witnesses will laugh.
     * @return
     */
    public List<Prank> generatePranks() {
        List<Prank> pranks = new ArrayList<Prank>();

        int numberOfGroups = config.getNumberOfGroups();
        int numberOfVictims = config.getVictims().getNumberOfPerson();

        // Create the List of groups of victims
        if (numberOfVictims / numberOfGroups < MIN_NUMBER_PERSON_PER_GROUP){
            numberOfGroups = numberOfVictims / 3;
            LOG.log(Level.SEVERE, "Sorry but with your configuration we can only generate {0} groups maximum, edit your configuration files in consequence.", numberOfGroups);
            return pranks; // Empty list
        }
        List<Group> groupsToPrank = createPrankGroups(config.getVictims(), numberOfGroups);
        LOG.info(String.valueOf(numberOfGroups) + " groups have been created with at least " + String.valueOf(MIN_NUMBER_PERSON_PER_GROUP) + "persons in it.");
        
        
        List<String> messages = config.getMessages();
        Collections.shuffle(messages);
        LOG.info("The message have been taken from the config file and shuffled.");
        
        // Take a victim as a sender and the rest as receivers.
        int messageIndex = 0;
        LOG.info("BEGIN : Generation of the pranks.");
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
        LOG.info("END : Generation of the pranks.");
        return pranks;
    }
}
