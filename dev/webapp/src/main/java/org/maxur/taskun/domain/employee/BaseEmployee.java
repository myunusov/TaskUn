package org.maxur.taskun.domain.employee;

import org.maxur.commons.domain.BaseEntity;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.MiddleName;
import org.maxur.taskun.domain.Name;

import javax.annotation.Nullable;
import javax.persistence.Transient;

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
    private Name firstName = Name.UNKNOWN;

    /**
     * The Employee's Last Name.
     */
    private Name lastName = Name.UNKNOWN;

    /**
     * The Employee's Middle Name.
     */
    private MiddleName middleName = MiddleName.UNKNOWN;

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
    public Name getFirstName() {
        return firstName;
    }

    /**
     * Getter for Last Name.
     *
     * @return The Employee's Last Name.
     */
    @Override
    public Name getLastName() {
        return lastName;
    }

    /**
     * Getter for Middle Name.
     *
     * @return The Employee's Middle Name.
     */
    @Override
    public MiddleName getMiddleName() {
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
        return Name.UNKNOWN.equals(getMiddleName())
                ? String.format("%s %s",
                getFirstName().asNormal(),
                getLastName().asNormal())
                : String.format("%s %s %s",
                getFirstName().asNormal(),
                getMiddleName().asNormal(),
                getLastName().asNormal());
    }


    /**
     * Setter for First Name.
     *
     * @param value The Employee's First Name
     */
    @Override
    public void setFirstName(final Name value) {
        assert (null != value);
        this.firstName = value;
    }

    /**
     * Setter for Last Name.
     *
     * @param value The Employee's Last Name
     */
    @Override
    public void setLastName(final Name value) {
        assert (null != value);
        this.lastName = value;
    }

    /**
     * Setter for Middle Name.
     *
     * @param value The Employee's Middle Name
     */
    @Override
    public void setMiddleName(@Nullable final MiddleName value) {
        final MiddleName name = (null == value ? MiddleName.UNKNOWN : value);
        if (gender == Gender.UNKNOWN) {
            gender = name.detectGenderForRU();
        }
        this.middleName = name;
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
