package model.mail;

import java.util.*;

/**
 * A class that represents a Group of Person in terms of e-mail properties.
 * A Group is just a list of Person. Each Person have a complete name
 * and an e-mail.
 *
 * @author Frueh Loïc, Dejvid Muaremi
 */

public class Group {
    
    /***
     * The representation of a group is an array list.
     */
    private final List<Person> group = new ArrayList<Person>();
    
    /***
     * Get the size of the Group.
     * @return the size of the Group.
     */
    public int getNumberOfPerson() {
        return group.size();
    }
    
    /***
     * Clear the Group of all its content.
     */
    public void clear() {
        group.clear();
    }
    
    /***
     * Add a person to the Group
     * @param person the person to add to the Group
     */
    public void addPerson(Person person){
        group.add(person);
    }
    
    /***
     * Add one or more persons to the Group.
     * @param persons one or more persons to add to the Group.
     */
    public void addMultiplePersons(Person... persons) {
        for (Person p : persons) {
            addPerson(p);
        }
    }
    
    /***
     * Add one or more person from a List of person to the Group.
     * @param persons a list of person to add.
     */
    public void addMultiplePersons(List<Person> persons){
        group.addAll(persons);
    }
    
    /***
     * Remove and return a person from the Group using its index.
     * @param index the index of the person to remove.
     * @return the person that has been removed or null if it can't be found.
     */
    public Person removePerson(int index) {
        if (0 < index && index < getNumberOfPerson()){
            return group.remove(index);
        }
        return null;
    }
    
    /***
     * Shuffle the content of the Group.
     */
    public void shuffle() {
        Collections.shuffle(group);
    }
    
    /***
     * Get a copy of the List of person.
     * @return a copy of the Group.
     */
    public List<Person> getGroup() {
        ArrayList<Person> group = new ArrayList<Person>();
        group.addAll(this.group);
        return group;
    }
    
    /***
     * Clear the Group and fill it with the new group.
     * @param group the List of persons to use as a Group.
     */
    public void setGroup(List<Person> group) {
        clear();
        addMultiplePersons(group);
    }
}
