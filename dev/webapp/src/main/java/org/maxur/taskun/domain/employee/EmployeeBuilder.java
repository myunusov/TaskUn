package org.maxur.taskun.domain.employee;

import org.maxur.commons.domain.EntityBuilder;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.MiddleName;
import org.maxur.taskun.domain.Name;

import javax.annotation.Nullable;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public abstract class EmployeeBuilder<T extends Employee> extends EntityBuilder<T> implements Employee {

    private static final long serialVersionUID = -6030778261130153455L;

    private Name firstName;

    private Name lastName;

    private MiddleName middleName = makeMiddleName(EMPTY);

    private Gender gender = Gender.UNKNOWN;

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