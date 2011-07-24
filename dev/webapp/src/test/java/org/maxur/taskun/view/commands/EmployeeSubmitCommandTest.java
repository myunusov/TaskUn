package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.view.model.EmployeeBean;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
@RunWith(JMock.class)
public class EmployeeSubmitCommandTest {

    private Mockery context;

    private AjaxRequestTarget target;

    private Model<EmployeeBean> mockModel;

    private EmployeeBean bean;

    @Before
    public void setUp() throws Exception {
         context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        target = context.mock(AjaxRequestTarget.class);
        mockModel = context.mock(Model.class, "mockModel");
        bean = context.mock(EmployeeBean.class);
    }

    @Test
    public void testExecute() throws Exception {
        final EmployeeSubmitCommand command = new EmployeeSubmitCommand();
        context.checking(new Expectations() {{
            oneOf(mockModel).getObject(); will(returnValue(bean));
            oneOf(bean).save(with(any(AjaxRequestTarget.class)));
        }});
        command.execute(target, mockModel);
        context.assertIsSatisfied();
    }
}
