package org.maxur.taskun.domain;

import javax.persistence.Transient;

/**
 * The Employee domain class.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public abstract class Employee implements Entity {

    /**
     * Getter for First Name.
     * @return The First Name.
     */
    public abstract String getFirstName();
    /**
     * Setter for First Name.
     * @param firstName The First Name.
     */
    public abstract void setFirstName(String firstName);
    /**
     * Getter for Last Name.
     * @return The Last Name.
     */
    public abstract String getLastName();
    /**
     * Setter for Last Name.
     * @param lastName The Last Name.
     */
    public abstract void setLastName(String lastName);
    /**
     * Getter for Middle Name.
     * @return The Middle Name.
     */
    public abstract String getMiddleName();
    /**
     * Setter for Middle Name.
     * @param middleName The Middle Name.
     */
    public abstract void setMiddleName(String middleName);

    /**
     * Getter for Employee's Title.
     * @return The Title of Employee.
     */
    @Transient
    public String getTitle() {
        return String.format("%s %s %s", getFirstName(), getMiddleName(), getLastName());
    }

}
