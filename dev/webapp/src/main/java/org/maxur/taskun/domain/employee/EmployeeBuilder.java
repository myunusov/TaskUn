package org.maxur.taskun.domain.employee;

import org.maxur.commons.domain.EntityBuilder;
import org.maxur.taskun.domain.Gender;

import javax.annotation.Nullable;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public abstract class EmployeeBuilder<T extends Employee> extends EntityBuilder<T> implements Employee {

    private static final long serialVersionUID = -6030778261130153455L;

    private String firstName;

    private String lastName;

    private String middleName;

    private Gender gender = Gender.UNKNOWN;

    @Override
    public T build() {
        final T employee;
        employee = make();
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        if (this.getMiddleName() != null) {
            employee.setMiddleName(this.middleName);
        }
        if (this.gender != Gender.UNKNOWN) {
            employee.setGender(this.gender);
        }
        return employee;
    }

    public EmployeeBuilder<T> withFirstName(final String name) {
        this.setFirstName(name);
        return this;
    }

    public EmployeeBuilder<T> withLastName(final String name) {
        setLastName(name);
        return this;
    }

    public EmployeeBuilder<T> withMiddleName(@Nullable final String name) {
        setMiddleName(name);
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
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getMiddleName() {
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
    public void setFirstName(String value) {
        firstName = value.toUpperCase();
    }

    @Override
    public void setLastName(String value) {
        lastName = value.toUpperCase();
    }

    @Override
    public void setMiddleName(@Nullable String value) {
        middleName = null != value ? value.toUpperCase() : EMPTY;
    }

    @Override
    public void setGender(Gender value) {
        gender = value;
    }
}