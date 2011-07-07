package org.maxur.taskun.domain;

import com.sun.istack.Nullable;
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
public abstract class Employee extends Entity {

    /**
     * The Employee's First Name.
     */
    @NotEmpty
    @Length(max = 50)
    private String firstName;
    /**
     * The Employee's Last Name.
     */
    @NotEmpty
    @Length(max = 50)
    private String lastName;
    /**
     * The Employee's Middle Name.
     */
    @Length(max = 50)
    private String middleName;


    /**
     * Getter for First Name.
     * @return The Employee's First Name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for Last Name.
     * @return The Employee's Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for Middle Name.
     * @return The Employee's Middle Name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for First Name.
     * @param value The Employee's First Name
     */
    public void setFirstName(final String value) {
        this.firstName = value;
    }

    /**
     * Setter for Last Name.
     * @param value The Employee's Last Name
     */
    public void setLastName(final String value) {
        this.lastName = value;
    }

    /**
     * Setter for Middle Name.
     * @param value The Employee's Middle Name
     */
    public void setMiddleName(@Nullable final String value) {
        this.middleName = value;
    }


    /**
     * Getter for Employee's Title.
     * @return The Title of Employee.
     */
    @Transient
    public String getTitle() {
        return String.format("%s %s %s", getFirstName(), getMiddleName(), getLastName());
    }


    /**
     * @see Object#toString()
     * @return  a string representation of the object.
     */
    @Override
    public final String toString() {
        return "Employee{"
                + "Id='" + getIdentifier() + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", middleName='" + middleName + '\''
                + '}';
    }



}
