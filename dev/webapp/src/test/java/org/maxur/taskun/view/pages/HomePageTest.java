package org.maxur.taskun.view.pages;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
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
import org.maxur.taskun.domain.AbstractEmployee;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.TaskunApplication;
import org.maxur.taskun.view.components.EmployeeForm;
import org.maxur.taskun.view.components.EmployeesView;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.model.UserSession;

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

    static private AbstractEmployee dummy = new AbstractEmployee() {
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
            oneOf(controller).getAllEmployee();
            will(returnValue(Collections.nCopies(0, dummy)));
        }});
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testBasePageBasicRenderWithEmployee() {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee();
            will(returnValue(Collections.nCopies(1, dummy)));
        }});
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testBasePageBasicRenderWithEmployeeMale() {
        final AbstractEmployee male = new AbstractEmployee() {
        };
        male.setGender(Gender.MALE);
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee();
            will(returnValue(Collections.nCopies(1, male)));
        }});
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testBasePageBasicRenderWithEmployeeFemale() {
        final AbstractEmployee female = new AbstractEmployee() {
        };
        female.setGender(Gender.FEMALE);
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee();
            will(returnValue(Collections.nCopies(1, female)));
        }});
        tester.startPage(HomePage.class);
        tester.assertRenderedPage(HomePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testBasePageComponents() {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee();
            will(returnValue(Collections.nCopies(0, null)));
        }});
        tester.startPage(HomePage.class);
        // assert rendered field components
        tester.assertComponent("menu", MenuPanel.class);
        tester.assertComponent("footer", FooterPanel.class);
        tester.assertComponent("header", HeaderPanel.class);
        tester.assertComponent("feedback", FeedbackPanel.class);
        tester.assertComponent("employees_title", Label.class);
        tester.assertComponent("employees_form", EmployeeForm.class);
        tester.assertComponent("employees", EmployeesView.class);
        tester.assertComponent("current_user", Label.class);

        // assert rendered label components
        tester.assertLabel("title", "ТаскУН: Управление задачами");
    }


    private class StubWebApplication extends TaskunApplication {

        private AnnotApplicationContextMock mockContext;

        @Override
        protected void springInjection() {
            mockContext = new AnnotApplicationContextMock();
            addComponentInstantiationListener(new SpringComponentInjector(this, mockContext, false));
        }

        public AnnotApplicationContextMock getMockContext() {
            return mockContext;
        }

        @Override
        public Session newSession(final Request request, final Response response) {
            return new UserSession(request, createMenuItems());
        }

        private MenuItems createMenuItems() {
            final MenuItems result = new MenuItems(3);
            result.add("Главная", HomePage.class, true);
            result.add("Блог", ExamplePage.class);
            result.add("О программе", ExamplePage.class);
            return result;
        }
    }
}
