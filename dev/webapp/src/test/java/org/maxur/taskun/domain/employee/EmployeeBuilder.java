package org.maxur.taskun.domain.employee;

import org.maxur.taskun.domain.Gender;

public class EmployeeBuilder {

    private final AbstractEmployee employee;

    public EmployeeBuilder() {
        employee = new AbstractEmployee() {
            private static final long serialVersionUID = -8678163562674459125L;
        };
    }

    public AbstractEmployee build() {
        return employee;
    }

    public EmployeeBuilder withFirstName(final String name) {
        employee.setFirstName(name);
        return this;
    }

    public EmployeeBuilder withLastName(final String name) {
        employee.setLastName(name);
        return this;
    }

    public EmployeeBuilder withMiddleName(final String name) {
        employee.setMiddleName(name);
        return this;
    }

    public EmployeeBuilder asFemale() {
        employee.setGender(Gender.FEMALE);
        return this;
    }

    public EmployeeBuilder asMale() {
        employee.setGender(Gender.MALE);
        return this;
    }
}