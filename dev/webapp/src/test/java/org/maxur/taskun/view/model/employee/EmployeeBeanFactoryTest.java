package org.maxur.taskun.view.model.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.pages.StubWebApplication;

import javax.validation.Validator;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public class EmployeeBeanFactoryTest {

    private AjaxRequestTarget target;

    private JUnit4Mockery context;

    private WicketTester tester;

    private ApplicationController controller;

    private EmployeesGroup group;

    private EmployeeBean bean;

    private Employee employee;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        tester = new WicketTester(new StubWebApplication());
        AnnotApplicationContextMock mockContext =
                ((StubWebApplication) tester.getApplication()).getMockContext();
        controller = context.mock(ApplicationController.class);
        mockContext.putBean("applicationController", controller);
        mockContext.putBean("validator", context.mock(Validator.class));
        group = context.mock(EmployeesGroup.class);
        employee = context.mock(Employee.class);
        target = context.mock(AjaxRequestTarget.class);
    }


    @Test
    public void testGetObject() throws Exception {
        final EmployeeBeanFactory factory = new EmployeeBeanFactory(group);
        context.checking(new Expectations() {{
            oneOf(controller).createEmployee();
        }});
        factory.getObject();
        context.assertIsSatisfied();
    }
}
