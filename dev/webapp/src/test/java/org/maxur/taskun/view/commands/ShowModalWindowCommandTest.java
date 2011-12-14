package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.view.components.BeanWindow;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.model.employee.EmployeeBeanFactory;
import org.maxur.taskun.view.pages.AbstractWicketTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
@RunWith(JMock.class)
public class ShowModalWindowCommandTest extends AbstractWicketTest {


    private AjaxRequestTarget target;

    private EmployeeBean mockModel;

    private BeanWindow bean;

    @Override
    protected void start() {
        target = context.mock(AjaxRequestTarget.class);
        mockModel = context.mock(EmployeeBean.class, "mockModel");
        bean = context.mock(BeanWindow.class);
    }

    @Test
    public void testExecute() throws Exception {
        final ShowModalWindowCommand command = new ShowModalWindowCommand(bean);
        context.checking(new Expectations() {{
            oneOf(bean).show(with(any(AjaxRequestTarget.class)), with(any(EmployeeBean.class)));
        }});
        command.execute(target, mockModel);
        context.assertIsSatisfied();
    }

    @Test
    public void testExecuteWithFactory() throws Exception {
        final ShowModalWindowCommand command = new ShowModalWindowCommand(bean);
        context.checking(new Expectations() {{
            oneOf(bean).show(with(any(AjaxRequestTarget.class)), with(any(EmployeeBeanFactory.class)));
        }});
        command.execute(target, new EmployeeBeanFactory(null));
        context.assertIsSatisfied();
    }


}
