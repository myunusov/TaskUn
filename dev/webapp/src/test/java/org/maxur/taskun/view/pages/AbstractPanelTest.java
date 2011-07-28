package org.maxur.taskun.view.pages;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.services.ApplicationController;

import javax.validation.Validator;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public abstract class AbstractPanelTest {

    protected WicketTester tester;

    protected Mockery context;

    protected ApplicationController controller;

    static protected Employee dummyEmployee = new AbstractEmployee() {
        private static final long serialVersionUID = 3908424889025108375L;
    };

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        tester = new WicketTester(new StubWebApplication());
        AnnotApplicationContextMock mockContext =
                ((StubWebApplication) tester.getApplication()).getMockContext();
        controller = context.mock(ApplicationController.class);
        mockContext.putBean("applicationController", controller);
        mockContext.putBean("validator", context.mock(Validator.class));
        startPanel();
    }

    protected abstract void startPanel();
}
