package model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a Group of Person in terms of e-mail properties.
 * A Group is just a list of Person. Each Person have a complete name
 * and an e-mail.
 *
 * @author Frueh Loïc
 */

public class Group {

    private final List<Person> group = new ArrayList<Person>();

    public int getNumberOfPerson() {
        return group.size();
    }

    public void clear() {
        group.clear();
    }

    public void addPerson(Person person){
        group.add(person);
    }

    public void addMultiplePersons(Person... persons) {
        for (Person p : persons) {
            addPerson(p);
        }
    }

    public void addMultiplePersons(List<Person> persons){
        group.addAll(persons);
    }

    public List<Person> getGroup() {
        ArrayList<Person> group = new ArrayList<Person>();
        group.addAll(this.group);
        return group;
    }

    public void setGroup(List<Person> group) {
        clear();
        addMultiplePersons(group);
    }
}
