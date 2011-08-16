package org.maxur.taskun.view.model.employee;

import org.jmock.Expectations;
import org.jmock.internal.ExpectationBuilder;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.services.ApplicationController;

import java.util.Collections;

public class EmployeeHelper {

    public static final Employee DUMMY_EMPLOYEE = new AbstractEmployee() {
        private static final long serialVersionUID = 3908424889025108375L;
    };

    public static final Employee MALE = new AbstractEmployee() {
        private static final long serialVersionUID = 1082151953627012291L;
    };

    static {
        MALE.setGender(Gender.MALE);
    }


    public static final Employee FEMALE = new AbstractEmployee() {
        private static final long serialVersionUID = 22556733695053292L;
    };

    static {
        FEMALE.setGender(Gender.FEMALE);
    }

    public static Expectations makeExpectations(
            final ApplicationController controller,
            final int numberOfItems,
            final Employee employee
    ) {
        return new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(numberOfItems, employee)));
        }};
    }

    public static ExpectationBuilder makeExpectations(ApplicationController controller, int numberOfItems) {
        return makeExpectations(controller, numberOfItems, DUMMY_EMPLOYEE);
    }

    public static EmployeeBean makeEmployeeBean(final EmployeesGroup group) {
        return new EmployeeBean(group, EmployeeHelper.DUMMY_EMPLOYEE);
    }


}