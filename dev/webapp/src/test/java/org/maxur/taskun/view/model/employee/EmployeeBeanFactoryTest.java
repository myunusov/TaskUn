package org.maxur.taskun.view.model.employee;

import org.apache.wicket.util.tester.WicketTester;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.view.pages.StubWebApplication;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public class EmployeeBeanFactoryTest {

    private JUnit4Mockery context;

    private EmployeesGroup group;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        new WicketTester(new StubWebApplication(context));
        group = context.mock(EmployeesGroup.class);
    }


    @Test
    public void testGetObject() throws Exception {
        final EmployeeBeanFactory factory = new EmployeeBeanFactory(group);
        factory.getObject();
        context.assertIsSatisfied();
    }
}
