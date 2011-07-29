package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.StubWebApplication;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@RunWith(JMock.class)
public class EmployeeListPanelTest {

    private WicketTester tester;

    private Mockery context;

    private ApplicationController controller;

    @Before
    public void setUp() {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        tester = new WicketTester(new StubWebApplication());
        AnnotApplicationContextMock mockContext =
                ((StubWebApplication) tester.getApplication()).getMockContext();
        controller = context.mock(ApplicationController.class);
        mockContext.putBean("applicationController", controller);
    }

    private void startPanel(final int numberOfItems) {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(numberOfItems, new DummyEmployee())));
        }});
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(String panelId) {
                return new EmployeeListPanel(panelId, new EmployeesGroup(), new CommandRepositoryImpl());
            }
        });
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

    private static class DummyEmployee extends AbstractEmployee {
    }
}
