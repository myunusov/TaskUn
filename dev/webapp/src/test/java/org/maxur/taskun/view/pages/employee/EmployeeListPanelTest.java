package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.services.ControllerExpectationBuilder;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@RunWith(JMock.class)
public class EmployeeListPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        final CommandRepositoryImpl dummyCommandRepository = new CommandRepositoryImpl();
        final EmployeesGroup dummyEmployeesGroup = new EmployeesGroup();
        return new EmployeeListPanel(panelId, dummyEmployeesGroup, dummyCommandRepository);
    }


    @Override
    protected void start() {
    }

    @Test
    public void testEmployeesList() {
        context.checking(new ControllerExpectationBuilder(application.controller).build());
        super.start();
        tester.assertComponent("panel:employee_list", WebMarkupContainer.class);
    }

    @Test
    public void testEmployees() {
        context.checking(new ControllerExpectationBuilder(application.controller).build());
        super.start();
        tester.assertComponent("panel:employee_list:employees", EmployeeListPanel.EmployeesView.class);
    }

    @Test
    public void testInvisibleNavigator() {
        context.checking(new ControllerExpectationBuilder(application.controller).count(1).build());
        super.start();
        tester.assertInvisible("panel:employee_list:navigator");
    }

    @Test
    public void testNavigator() {
        context.checking(new ControllerExpectationBuilder(application.controller).count(100).build());
        super.start();
        tester.assertComponent("panel:employee_list:navigator", HiddenPagingNavigator.class);
    }

    @Test
    public void testWicketPanel() throws Exception {
        context.checking(new ControllerExpectationBuilder(application.controller).build());
        super.start();
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }


}
