package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
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
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.model.EmployeesGroup;

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
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee();
            will(returnValue(Collections.nCopies(0, null)));
        }});
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(String panelId) {
                return new EmployeeListPanel(panelId, new EmployeesGroup(controller));
            }
        });
    }

    @Test
    public void testPanelBasicRender() {
        tester.assertComponent("panel:employees", EmployeeListPanel.EmployeesView.class);
        tester.assertComponent("panel:employee_list_title", Label.class);
    }

}
