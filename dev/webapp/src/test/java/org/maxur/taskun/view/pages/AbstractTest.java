package org.maxur.taskun.view.pages;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.services.ApplicationController;

import javax.validation.Validator;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/15/11
 */
@RunWith(JMock.class)
public abstract class AbstractTest {

    protected WicketTester tester;

    protected Mockery context;

    protected ApplicationController controller;


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
        start();
    }

    protected abstract void start();

    @Test
    public void testNoneError() throws Exception {
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

}
