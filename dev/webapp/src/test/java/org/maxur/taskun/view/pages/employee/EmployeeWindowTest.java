package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.jmock.Expectations;
import org.junit.Test;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.pages.AbstractTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public class EmployeeWindowTest extends AbstractTest {

    private EmployeeWindow window;

    @Override
    protected void start() {
        EmployeeBean bean = context.mock(EmployeeBean.class);
        final Employee employee = context.mock(Employee.class);
        CommandRepositoryImpl repository = context.mock(CommandRepositoryImpl.class);
        context.checking(new Expectations() {{
        }});
/*        tester.start(new ITestPanelSource() {    //TODO
            @Override
            public Panel getTestPanel(String panelId) {
                return new EmployeePanel(panelId, bean, null, null);
            }
        });*/
        window = new EmployeeWindow("1", repository);
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testShow() throws Exception {
        final AjaxRequestTarget target = context.mock(AjaxRequestTarget.class);
        window.show(target);
    }




}
