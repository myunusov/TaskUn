package org.maxur.taskun.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Nullable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * The Employee domain class.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public abstract class AbstractEmployee extends AbstractEntity implements Employee {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -4499503762661672297L;

    /**
     * Empty String Constant.
     */
    private static final String EMPTY_STRING = "";

    /**
     * The Employee's First Name.
     */
    @NotEmpty
    @Length(max = MAX_EMPLOYEE_NAME_LENGTH)
    private String firstName = EMPTY_STRING;

    /**
     * The Employee's Last Name.
     */
    @NotEmpty
    @Length(max = MAX_EMPLOYEE_NAME_LENGTH)
    private String lastName = EMPTY_STRING;

    /**
     * The Employee's Middle Name.
     */
    @Length(max = MAX_EMPLOYEE_NAME_LENGTH)
    private String middleName = EMPTY_STRING;

    /**
     * The Employee's Gender.
     */
    @NotNull
    private Gender gender = Gender.UNKNOWN;

    /**
     * Getter for First Name.
     *
     * @return The Employee's First Name.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for Last Name.
     *
     * @return The Employee's Last Name.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for Middle Name.
     *
     * @return The Employee's Middle Name.
     */
    @Override
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Getter for Gender.
     *
     * @return The Employee's Gender.
     */
    @Override
    public Gender getGender() {
        return gender;
    }

    /**
     * Setter for First Name.
     *
     * @param value The Employee's First Name
     */
    @Override
    public void setFirstName(final String value) {
        this.firstName = value;
    }

    /**
     * Setter for Last Name.
     *
     * @param value The Employee's Last Name
     */
    @Override
    public void setLastName(final String value) {
        this.lastName = value;
    }

    /**
     * Setter for Middle Name.
     *
     * @param value The Employee's Middle Name
     */
    @Override
    public void setMiddleName(@Nullable final String value) {
        if (null != value && gender == Gender.UNKNOWN) {
            if (value.endsWith("вич")) {
                gender = Gender.MALE;
            } else if (value.endsWith("вна")) {
                gender = Gender.FEMALE;
            }
        }
        this.middleName = value == null ? EMPTY_STRING : value;
    }

    /**
     * Setter for Gender.
     *
     * @param value The Employee's Gender
     */
    @Override
    public void setGender(final Gender value) {
        this.gender = value;
    }

    /**
     * Getter for Employee's Title.
     *
     * @return The Title of Employee.
     */
    @Override
    @Transient
    public final String getTitle() {
        return EMPTY_STRING.equals(getMiddleName())
                ? String.format("%s %s", getFirstName(), getLastName())
                : String.format("%s %s %s", getFirstName(), getMiddleName(), getLastName());
    }

    /**
     * @return a string representation of the object.
     * @see Object#toString()
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
