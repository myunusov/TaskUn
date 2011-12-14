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
import org.maxur.taskun.view.model.employee.EmployeesGroup;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
@RunWith(JMock.class)
public class EmployeeRemoveCommandTest {

    private Mockery context;

    private AjaxRequestTarget target;

    private EmployeesGroup mockModel;


    @Before
    public void setUp() throws Exception {
         context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        target = context.mock(AjaxRequestTarget.class);
        mockModel = context.mock(EmployeesGroup.class, "mockModel");
    }

    @Test
    public void testExecute() throws Exception {
        final EmployeeRemoveCommand command = new EmployeeRemoveCommand();
        context.checking(new Expectations() {{
            oneOf(mockModel).removeSelected(with(any(AjaxRequestTarget.class)));
        }});
        command.execute(target, mockModel);
        context.assertIsSatisfied();
    }
}
