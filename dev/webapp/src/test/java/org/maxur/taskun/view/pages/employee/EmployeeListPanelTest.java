package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.AbstractPanelTest;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@RunWith(JMock.class)
public class EmployeeListPanelTest extends AbstractPanelTest {

    static protected Employee dummyEmployee = new AbstractEmployee() {
        private static final long serialVersionUID = 3908424889025108375L;
    };

    @Override
    protected Panel makeTestPanel(String panelId) {
        final CommandRepositoryImpl dummyCommandRepository = new CommandRepositoryImpl();
        final EmployeesGroup dummyEmployeesGroup = new EmployeesGroup();
        return new EmployeeListPanel(panelId, dummyEmployeesGroup, dummyCommandRepository);
    }


    @Override
    protected void start() {
    }

    private void startPanel(final int numberOfItems) {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(numberOfItems, dummyEmployee)));
        }});
        super.start();
    }

    @Test
    public void testEmployeesList() {
        startPanel(0);
        tester.assertComponent("panel:employee_list", WebMarkupContainer.class);
    }

    @Test
    public void testEmployees() {
        startPanel(0);
        tester.assertComponent("panel:employee_list:employees", EmployeeListPanel.EmployeesView.class);
    }

    @Test
    public void testInvisibleNavigator() {
        startPanel(1);
        tester.assertInvisible("panel:employee_list:navigator");
    }

    @Test
    public void testNavigator() {
        startPanel(100);
        tester.assertComponent("panel:employee_list:navigator", HiddenPagingNavigator.class);
    }

    @Test
    public void testWicketPanel() throws Exception {
        startPanel(0);
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }


}
