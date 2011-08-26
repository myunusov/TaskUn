package org.maxur.taskun.services.Impl;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.service.Logger;
import org.maxur.taskun.AbstractMockTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/18/11
 */
public class NotifierImplTest extends AbstractMockTest {

    private NotifierImpl notifier;

    private Logger logger;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        logger = context.mock(Logger.class);
        notifier = new NotifierImpl(logger);
    }

    @Test
    public void testNotifyEmployeeCreate() throws Exception {
        context.checking(new Expectations() {{
            exactly(1).of(logger).debug(with(any(Class.class)), with(any(String.class)));
        }});
        notifier.notifyEmployeeCreate(NotifierImplTest.class, "A");
        context.assertIsSatisfied();
    }

}
