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
