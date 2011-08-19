package org.maxur.taskun.services.impl;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.AbstractMockTest;
import org.slf4j.Logger;

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
        notifier = new NotifierImpl();
        logger = context.mock(Logger.class);
        notifier.addLogger(NotifierImplTest.class.getName(), logger);
    }

    @Test
    public void testNotifyEmployeeCreate() throws Exception {
        context.checking(new Expectations() {{
            exactly(1).of(logger).debug(with(any(String.class)));
        }});
        notifier.notifyEmployeeCreate(NotifierImplTest.class);
        context.assertIsSatisfied();
    }

}
