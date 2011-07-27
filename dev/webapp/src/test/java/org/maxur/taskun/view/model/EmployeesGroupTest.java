package org.maxur.taskun.view.model;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.StubWebApplication;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class EmployeesGroupTest {

    private EmployeesGroup group;

    @Before
    public void setUp() throws Exception {
        Mockery context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        final ApplicationController controller = context.mock(ApplicationController.class);
        WicketTester tester = new WicketTester(new StubWebApplication());
        AnnotApplicationContextMock mockContext =
                ((StubWebApplication) tester.getApplication()).getMockContext();
        mockContext.putBean("applicationController", controller);
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.<AbstractEmployee>nCopies(5, new AbstractEmployee(){})));
        }});
        group = new EmployeesGroup();
    }

    @Test
    public void testGetTotal() throws Exception {
        Assert.assertEquals(5, group.size());
    }

    @Test
    public void testGetAll() throws Exception {
        Assert.assertEquals(5, group.getObject().size());
    }

    @Test
    public void testSelectedCount() throws Exception {
        Assert.assertEquals(new Integer(0), group.getSelectedCount());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testModifyEmployeeList() throws Exception {
        group.getObject().add(null);
    }


}
