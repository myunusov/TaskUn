package org.maxur.taskun.domain;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
public class Employee {

    private String firstName;
    private String lastName;
    private String middleName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Object getTitle() {
        return String.format("%s %s %s", firstName, middleName, lastName);
    }
}
