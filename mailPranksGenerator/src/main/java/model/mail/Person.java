package model.mail;

/**
 * A class that represents a Person in terms of e-mail properties.
 * A Person in that case is represented by a First Name, a Last Name
 * and an e-mail.
 *
 * @author Frueh Loïc, Dejvid Muaremi
 */

public class Person {

    private String firstName;
    private String lastName;
    private String email;
    
    /***
     * Default constructor for a Person.
     * Nothing will be set.
     */
    public Person() {}
    
    /***
     * Create a person from his email.
     * We supposed that the mails are in the format : firstname.lastname@company.domain
     * @param email the email to use to make a Person.
     */
    public Person(String email) {
        int splitIndex = email.indexOf('@');
        String name = email.substring(0, splitIndex);
        String[] names = name.split("\\.");

        if (names.length > 1) {
            firstName = names[0].substring(0, 1).toUpperCase() + names[0].substring(1);
            lastName = names[1].substring(0, 1).toUpperCase() + names[1].substring(1);
        }
        else {
            firstName = name.substring(0,1).toUpperCase() + name.substring(1);
            lastName = "";
        }

        this.email = email;
    }
    
    /***
     * Create a person from his first name, last name, and email.
     * With this one, you can specify all the fields you want.
     * @param firstName the first name of the new person.
     * @param lastName the last name of the new person.
     * @param email the email address of the new person.
     */
    public Person (String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    /***
     * Get the first name of the person.
     * @return the first name of a person.
     */
    public String getFirstName() {
        return firstName;
    }
    
    /***
     * Get the last name of the person.
     * @return the last name of a person.
     */
    public String getLastName() {
        return lastName;
    }
    
    /***
     * Get the email address of the person.
     * @return the email address of a person.
     */
    public String getEmail() {
        return email;
    }
    
    /***
     * Set the first name of the person.
     * @param firstName the first name of a person.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /***
     * Set the last name of the person.
     * @param lastName the first name of a person.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /***
     * Set the email address of the person.
     * @param email the email address of a person.
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
