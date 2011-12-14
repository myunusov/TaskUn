package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.view.model.employee.EmployeeBean;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
@RunWith(JMock.class)
public class EmployeeSelectCommandTest {

    private Mockery context;

    private AjaxRequestTarget target;

    private EmployeeBean employeeBean;

    @Before
    public void setUp() throws Exception {
         context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        target = context.mock(AjaxRequestTarget.class);
        employeeBean = context.mock(EmployeeBean.class, "mockModel");
    }

    @Test
    public void testExecute() throws Exception {
        final EmployeeSelectCommand command = new EmployeeSelectCommand();
        context.checking(new Expectations() {{
            oneOf(employeeBean).select(with(any(AjaxRequestTarget.class)));
        }});
        command.execute(target, employeeBean);
        context.assertIsSatisfied();
    }
}
