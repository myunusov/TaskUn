/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.taskun.domain.employee;

import org.maxur.commons.domain.EntityBuilder;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.MiddleName;
import org.maxur.taskun.domain.Name;

import javax.annotation.Nullable;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public abstract class EmployeeBuilder<T extends Employee> extends EntityBuilder<T> implements Employee {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -6030778261130153455L;

    /**
     * The First Name Value Object.
     */
    private Name firstName;

    /**
     * The Last Name Value Object.
     */
    private Name lastName;

    /**
     * The Middle Name Value Object.
     */
    private MiddleName middleName = makeMiddleName(EMPTY);

    /**
     * The Employees Gender.
     */
    private Gender gender = Gender.UNKNOWN;

    /**
     * The factory method for creating Employees Name.
     *
     * @param name The string presentation of name.
     * @return The value object presentation of name.
     */
    public abstract Name makeName(final String name);

    /**
     * The factory method for creating Employees MiddleName.
     *
     * @param name The string presentation of middle name.
     * @return The value object presentation of middle name.
     */
    public abstract MiddleName makeMiddleName(final String name);


    @Override
    public T build() {
        final T employee;
        employee = make();
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setMiddleName(this.middleName);
        if (this.gender != Gender.UNKNOWN) {
            employee.setGender(this.gender);
        }
        return employee;
    }

    public EmployeeBuilder<T> withFirstName(final String name) {
        this.setFirstName(makeName(name));
        return this;
    }


    public EmployeeBuilder<T> withLastName(final String name) {
        setLastName(makeName(name));
        return this;
    }

    public EmployeeBuilder<T> withMiddleName(@Nullable final String name) {
        setMiddleName(makeMiddleName(name));
        return this;
    }


    public EmployeeBuilder<T> asFemale() {
        this.gender = Gender.FEMALE;
        return this;
    }

    public EmployeeBuilder<T> asMale() {
        this.gender = Gender.MALE;
        return this;
    }

    @Override
    public Name getFirstName() {
        return firstName;
    }

    @Override
    public Name getLastName() {
        return lastName;
    }

    @Override
    public MiddleName getMiddleName() {
        return middleName;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setFirstName(final Name value) {
        firstName = value;
    }

    @Override
    public void setLastName(Name value) {
        lastName = value;
    }

    @Override
    public void setMiddleName(@Nullable MiddleName value) {
        if (null != value) {
            middleName = value;
        }
    }

    @Override
    public void setGender(Gender value) {
        gender = value;
    }


}