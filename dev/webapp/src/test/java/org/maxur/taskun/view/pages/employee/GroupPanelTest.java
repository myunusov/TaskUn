package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.Component;
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
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.StubWebApplication;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@RunWith(JMock.class)
public class GroupPanelTest {

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
    }

    private void startPanel() {
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(String panelId) {
                return new GroupPanel(panelId, new EmployeesGroup(), null);
            }
        });
    }

    @Test
    public void testAppNameLabel() throws Exception {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(0, null)));
        }});
        startPanel();
        tester.assertComponent("panel:resume_title", Label.class);
        tester.assertComponent("panel:total", Label.class);
        tester.assertComponent("panel:selected", Label.class);
        tester.assertComponent("panel:opp_title", Label.class);
    }

    @Test
    public void testRemoveOnEmployeesIsEmpty() {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(0, null)));
        }});
        startPanel();
        tester.assertInvisible("panel:remove_item");
    }

    @Test
    public void testRemoveOnEmployeesIsNotEmpty() {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(1, dummy)));
        }});
        startPanel();
        final Component panel = tester.getComponentFromLastRenderedPage("panel");
        final EmployeesGroup model = (EmployeesGroup) panel.getDefaultModel();
        model.getObject().get(0).select(null);
        tester.assertComponent("panel:remove_item", AjaxMarkupContainer.class);
    }


}
