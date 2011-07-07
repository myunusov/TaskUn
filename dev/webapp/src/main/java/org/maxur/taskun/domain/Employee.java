package org.maxur.taskun.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

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
    @NotEmpty
    @Length(max = 50)
    public abstract String getFirstName();
    /**
     * Getter for Last Name.
     * @return The Last Name.
     */
    @NotEmpty
    @Length(max = 50)
    public abstract String getLastName();
    /**
     * Getter for Middle Name.
     * @return The Middle Name.
     */
    @Length(max = 50)
    public abstract String getMiddleName();

    /**
     * Setter for First Name.
     * @param firstName The First Name.
     */
    public abstract void setFirstName(String firstName);

    /**
     * Setter for Last Name.
     * @param lastName The Last Name.
     */
    public abstract void setLastName(String lastName);
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
