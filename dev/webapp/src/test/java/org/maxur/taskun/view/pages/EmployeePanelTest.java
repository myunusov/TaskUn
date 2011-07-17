package org.maxur.taskun.view.pages;

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
                return new EmployeePanel(panelId, new EmployeeBean(controller, dummy), HomePage.class);
            }
        });
    }

    @Test
    public void testPanelBasicRender() {
        tester.assertComponent("panel:feedback", FeedbackPanel.class);
        tester.assertComponent("panel:employee_form", Form.class);
    }

}
