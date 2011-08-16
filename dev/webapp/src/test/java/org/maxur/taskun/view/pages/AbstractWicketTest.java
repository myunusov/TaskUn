package org.maxur.taskun.view.pages;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.AbstractMockTest;
import org.maxur.taskun.services.ApplicationController;

import javax.validation.Validator;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/15/11
 */
public abstract class AbstractWicketTest extends AbstractMockTest {

    protected WicketTester tester;

    protected ApplicationController controller;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
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
