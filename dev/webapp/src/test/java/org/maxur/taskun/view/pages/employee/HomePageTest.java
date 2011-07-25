package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
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
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.pages.FooterPanel;
import org.maxur.taskun.view.pages.HeaderPanel;
import org.maxur.taskun.view.pages.MenuPanel;
import org.maxur.taskun.view.pages.StubWebApplication;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(JMock.class)
public class HomePageTest {

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

    @Test
    public void testBasePageBasicRender() {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(0, dummy)));
        }});
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testBasePageBasicRenderWithEmployee() {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(1, dummy)));
        }});
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testBasePageBasicRenderWithEmployeeMale() {
        final Employee male = new AbstractEmployee() {
        };
        male.setGender(Gender.MALE);
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(1, male)));
        }});
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testBasePageBasicRenderWithEmployeeFemale() {
        final Employee female = new AbstractEmployee() {
        };
        female.setGender(Gender.FEMALE);
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(1, female)));
        }});
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testBasePageComponents() {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(0, null)));
        }});
        tester.startPage(HomePage.class);
        // assert rendered field components
        tester.assertComponent("menu", MenuPanel.class);
        tester.assertComponent("footer", FooterPanel.class);
        tester.assertComponent("header", HeaderPanel.class);


        // assert rendered label components
        tester.assertLabel("title", "ТаскУН: Управление задачами");
    }


}
