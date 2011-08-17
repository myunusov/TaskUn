package org.maxur.taskun.view.model.employee;

import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;

public class EmployeeBuilder {

    private Gender gender = Gender.UNKNOWN;


    public Employee build() {
        final AbstractEmployee employee = new AbstractEmployee() {
            private static final long serialVersionUID = 3908424889025108375L;
        };
        employee.setGender(gender);
        return employee;
    }

    public EmployeeBuilder asMale() {
        this.gender = Gender.MALE;
        return this;
    }

    public EmployeeBuilder asFemale() {
        this.gender = Gender.FEMALE;
        return this;
    }

    public static EmployeeBean makeEmployeeBean(final EmployeesGroup group) {
        return new EmployeeBean(group, new EmployeeBuilder().build());
    }

    public static EmployeeBean makeEmployeeBean() {
        return new EmployeeBean(null, new EmployeeBuilder().build()) {
        };
    }

}