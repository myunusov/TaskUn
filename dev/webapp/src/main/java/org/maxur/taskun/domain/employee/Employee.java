package org.maxur.taskun.domain.employee;

import org.maxur.commons.validator.Unique;
import org.maxur.commons.domain.Entity;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.MiddleName;
import org.maxur.taskun.domain.Name;

import javax.annotation.Nullable;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * The Employee Interface.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@Unique(properties = {"firstName", "lastName", "middleName"})
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
     * Getter for the Employee's First Name.
     *
     * @return The Employee's First Name.
     */
    @NotNull
    @Valid
    Name getFirstName();

    /**
     * Getter for the Employee's Last Name.
     *
     * @return The Employee's Last Name.
     */
    @NotNull
    @Valid
    Name getLastName();

    /**
     * Getter for the Employee's Middle Name.
     *
     * @return The Employee's Middle Name.
     */
    @NotNull
    @Valid
    MiddleName getMiddleName();

    /**
     * Getter for the Employee's Gender.
     *
     * @return The Employee's Gender.
     */
    @NotNull
    Gender getGender();

    /**
     * Getter for the Employee's Title.
     *
     * @return The Employee's Title.
     */
    @Transient
    String getTitle();


    /**
     * Setter for the Employee's First Name.
     *
     * @param value The Employee's First Name.
     */
    void setFirstName(Name value);

    /**
     * Setter for the Employee's Last Name.
     *
     * @param value The Employee's Last Name.
     */
    void setLastName(Name value);

    /**
     * Setter for the Employee's Middle Name.
     *
     * @param value The Employee's Middle Name.
     */
    void setMiddleName(@Nullable MiddleName value);

    /**
     * Setter for the Employee's Gender.
     *
     * @param value The Employee's Gender.
     */
    void setGender(Gender value);

}
