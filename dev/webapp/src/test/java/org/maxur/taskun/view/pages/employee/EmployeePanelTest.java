package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.AbstractEmployee;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.model.EmployeeBean;
import org.maxur.taskun.view.pages.StubWebApplication;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@RunWith(JMock.class)
public class EmployeePanelTest {

    private WicketTester tester;

    private Mockery context;

    private ApplicationController controller;

    static private Employee dummy = new AbstractEmployee() {
        private static final long serialVersionUID = 3908424889025108375L;
    };

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
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(String panelId) {
                return new EmployeePanel(panelId, new EmployeeBean(null, dummy){}, null, null);
            }
        });
    }

    @Test
    public void testPanelBasicRender() {
        tester.assertComponent("panel:employee_feedback", FeedbackPanel.class);
        tester.assertComponent("panel:employee_form", Form.class);
    }


    @Test
    public void testFirstNameLabel() throws Exception {
        tester.assertComponent("panel:employee_form:first_name", Label.class);
    }

    @Test
    public void testLastNameLabel() throws Exception {
        tester.assertComponent("panel:employee_form:last_name", Label.class);
    }

    @Test
    public void testMiddleNameLabel() throws Exception {
        tester.assertComponent("panel:employee_form:middle_name", Label.class);
    }

    @Test
    public void testWicketPanel() throws Exception {
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

}
