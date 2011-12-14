package org.maxur.taskun.view.pages;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.AbstractMockTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/15/11
 */
public abstract class AbstractWicketTest extends AbstractMockTest {

    protected WicketTester tester;

    protected StubWebApplication application;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        tester = new WicketTester(new StubWebApplication(context));
        application = (StubWebApplication) tester.getApplication();
        start();
    }

    protected abstract void start();

    @Test
    public void testNoneError() throws Exception {
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

}
