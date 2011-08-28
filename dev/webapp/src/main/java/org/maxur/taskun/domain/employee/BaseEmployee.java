package org.maxur.taskun.domain.employee;

import org.apache.commons.lang3.StringUtils;
import org.maxur.commons.domain.BaseEntity;
import org.maxur.taskun.domain.Gender;

import javax.annotation.Nullable;
import javax.persistence.Transient;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * The Employee domain class.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public class BaseEmployee extends BaseEntity implements Employee {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -4499503762661672297L;


    /**
     * The Employee's First Name.
     */
    private String firstName = EMPTY;

    /**
     * The Employee's Last Name.
     */
    private String lastName = EMPTY;

    /**
     * The Employee's Middle Name.
     */
    private String middleName = EMPTY;

    /**
     * The Employee's Gender.
     */
    private Gender gender = Gender.UNKNOWN;

    public BaseEmployee(final String id) {
        super(id);
    }

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

    @Override
    public boolean isNew() {
        return false;
    }

    /**
     * Getter for Employee's Title.
     *
     * @return The Title of Employee.
     */
    @Override
    @Transient
    public final String getTitle() {
        return EMPTY.equals(getMiddleName())
                ? String.format("%s %s", toName(getFirstName()), toName(getLastName()))
                : String.format("%s %s %s",
                toName(getFirstName()),
                toName(getMiddleName()),
                toName(getLastName())
        );
    }


    /**
     * Setter for First Name.
     *
     * @param value The Employee's First Name
     */
    @Override
    public void setFirstName(final String value) {
        assert (null != value);
        this.firstName = value.toUpperCase();
    }

    /**
     * Setter for Last Name.
     *
     * @param value The Employee's Last Name
     */
    @Override
    public void setLastName(final String value) {
        assert (null != value);
        this.lastName = value.toUpperCase();
    }

    /**
     * Setter for Middle Name.
     *
     * @param value The Employee's Middle Name
     */
    @Override
    public void setMiddleName(@Nullable final String value) {
        final String name = null == value ? EMPTY : value.toUpperCase();
        detectGenderByRusMiddleName(name);
        this.middleName = name;
    }

    private void detectGenderByRusMiddleName(final String value) {
        if (gender == Gender.UNKNOWN) {
            if (value.endsWith("ВИЧ")) {
                gender = Gender.MALE;
            } else if (value.endsWith("ВНА")) {
                gender = Gender.FEMALE;
            }
        }
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


    private String toName(final String name) {
        return StringUtils.capitalize(name.toLowerCase());
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
