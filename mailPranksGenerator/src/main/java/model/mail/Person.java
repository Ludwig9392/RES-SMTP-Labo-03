package model.mail;

/**
 * A class that represents a Person in terms of e-mail properties.
 * A Person in that case is represented by a First Name, a Last Name
 * and a e-mail.
 *
 * @author Frueh Loïc
 */

public class Person {

    private String firstName;
    private String lastName;
    private String email;


    public Person() {}

    public Person(String email) {
        int splitIndex = email.indexOf('@');
        String name = email.substring(0, splitIndex);
        String[] names = name.split("\\.");

        if (names.length > 1) {
            firstName = names[0].substring(0, 1).toUpperCase() + names[0].substring(1);
            lastName = names[1].substring(0, 1).toUpperCase() + names[1].substring(1);
        }
        else {
            firstName = name.substring(0,1) + name.substring(1);
            lastName = "";
        }

        this.email = email;
    }

    public Person (String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
