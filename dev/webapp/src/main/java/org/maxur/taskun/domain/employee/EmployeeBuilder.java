package org.maxur.taskun.domain.employee;

import org.maxur.taskun.domain.Gender;

import javax.annotation.Nullable;

public abstract class EmployeeBuilder<T extends Employee> implements Employee {

    private static final long serialVersionUID = -6030778261130153455L;

    private String firstName;

    private String lastName;

    private String middleName;

    private Gender gender;

    protected abstract T make();

    public T build() {
        final T employee;
        employee = make();
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        if (this.middleName != null) {
            employee.setMiddleName(this.middleName);
        }
        if (this.gender != null) {
            employee.setGender(this.gender);
        }
        return employee;
    }

    public EmployeeBuilder withFirstName(final String name) {
        this.firstName = name;
        return this;
    }

    public EmployeeBuilder withLastName(final String name) {
        lastName = name;
        return this;
    }

    public EmployeeBuilder withMiddleName(@Nullable final String name) {
        middleName = name;
        return this;
    }

    public EmployeeBuilder asFemale() {
        this.gender = Gender.FEMALE;
        return this;
    }

    public EmployeeBuilder asMale() {
        this.gender = Gender.MALE;
        return this;
    }

    @Override
    public String getIdentifier() {
        return null;
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
        firstName = value;
    }

    @Override
    public void setLastName(String value) {
        lastName = value;
    }

    @Override
    public void setMiddleName(@Nullable String value) {
        middleName = value;
    }

    @Override
    public void setGender(Gender value) {
        gender = value;
    }
}