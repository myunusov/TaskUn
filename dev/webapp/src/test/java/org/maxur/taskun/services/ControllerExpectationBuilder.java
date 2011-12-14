package org.maxur.taskun.services;

import org.jmock.Expectations;
import org.maxur.commons.domain.Specification;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.model.employee.EmployeeBuilder;

import java.util.Collections;

public class ControllerExpectationBuilder {

    private final ApplicationController controller;

    private Employee employee = new EmployeeBuilder().build();

    private int count = 0;

    public ControllerExpectationBuilder(final ApplicationController controller) {
        this.controller = controller;
    }

    public ControllerExpectationBuilder fromEmployee(final Employee employee) {
        this.employee = employee;
        return this;
    }

    public ControllerExpectationBuilder count(final int count) {
        this.count = count;
        return this;
    }

    public Expectations build() {
        return new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(count, employee)));
        }};
    }

}