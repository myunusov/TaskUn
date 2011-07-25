package org.maxur.taskun.domain.employee;

import org.maxur.taskun.domain.Entity;
import org.maxur.taskun.domain.Gender;

import javax.annotation.Nullable;
import javax.persistence.Transient;

/**
 * The Employee Interface.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public interface Employee extends Entity {
    /**
     * Min length of employee names.
     */
    int MIN_EMPLOYEE_NAME_LENGTH = 1;
    /**
     * Max length of employee names.
     */
    int MAX_EMPLOYEE_NAME_LENGTH = 50;

    /**
     * Getter for the Employee's Identifier.
     * @return The Employee's Identifier.
     */
    String getIdentifier();

    /**
     * Getter for the Employee's First Name.
     * @return The Employee's First Name.
     */
    String getFirstName();

    /**
     * Getter for the Employee's Last Name.
     * @return The Employee's Last Name.
     */
    String getLastName();

    /**
     * Getter for the Employee's Middle Name.
     * @return The Employee's Middle Name.
     */
    String getMiddleName();

    /**
     * Getter for the Employee's Gender.
     * @return The Employee's Gender.
     */
    Gender getGender();

    /**
     * Getter for the Employee's Title.
     * @return The Employee's Title.
     */
    @Transient
    String getTitle();


    /**
     * Setter for the Employee's First Name.
     * @param value The Employee's First Name.
     */
    void setFirstName(String value);

    /**
     * Setter for the Employee's Last Name.
     * @param value The Employee's Last Name.
     */
    void setLastName(String value);

    /**
     * Setter for the Employee's Middle Name.
     * @param value The Employee's Middle Name.
     */
    void setMiddleName(@Nullable String value);

    /**
     * Setter for the Employee's Gender.
     * @param value The Employee's Gender.
     */
    void setGender(Gender value);

}
