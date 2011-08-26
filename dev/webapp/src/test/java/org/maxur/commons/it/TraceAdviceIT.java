package org.maxur.commons.it;

import org.aspectj.lang.Aspects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.advice.TraceAdvice;
import org.maxur.commons.service.impl.BaseLogger;
import org.mockito.InOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/23/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"test-aspect-config.xml"})
public class TraceAdviceIT {

    @Autowired
    private Starter starter;

    private InOrder order;

    private BaseLogger logger;

    @Before
    public void setUp() throws Exception {
        logger = mock(BaseLogger.class);
        final TraceAdvice traceAdvice = Aspects.aspectOf(TraceAdvice.class);
        traceAdvice.setLogger(logger);
        order = inOrder(logger);
    }

    @Test
    public void shouldBeLogDebugMessageOnTraceMethod() throws Exception {
        starter.doTrace();
        order.verify(logger).debug("trace", "Fake.trace() ENTER");
        order.verify(logger).debug("trace", "Fake.trace() EXIT");
    }

    @Test
    public void shouldBeLogDebugMessageOnTraceMethodWithReturnValue() throws Exception {
        starter.doTraceWithReturn();
        order.verify(logger).debug("trace", "Fake.traceWithReturn() ENTER");
        order.verify(logger).debug("trace", "Fake.traceWithReturn() EXIT");
    }

    @Test
    public void shouldBeLogDebugMessageOnTraceMethodWithArguments() throws Exception {
        starter.doTraceWithArguments();
        order.verify(logger).debug("trace", "Fake.traceWithArguments(..) ENTER");
        order.verify(logger).debug("trace", "Fake.traceWithArguments(..) EXIT");
    }

    @Test
    public void shouldBeNoInteractionOnDirectCall() throws Exception {
        starter.doDirectTrace();
        verifyNoMoreInteractions(logger);
    }

}

