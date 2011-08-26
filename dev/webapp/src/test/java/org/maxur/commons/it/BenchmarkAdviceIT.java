package org.maxur.commons.it;

import org.aspectj.lang.Aspects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.advice.BenchmarkAdvice;
import org.maxur.commons.advice.StopWatchFactory;
import org.maxur.commons.service.impl.BaseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/23/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"test-aspect-config.xml"})
public class BenchmarkAdviceIT {

    @Autowired
    private Starter starter;

    private BaseLogger logger;

    @Before
    public void setUp() throws Exception {
        final StopWatch stopWatch = mock(StopWatch.class);
        logger = mock(BaseLogger.class);
        final BenchmarkAdvice advice = Aspects.aspectOf(BenchmarkAdvice.class);
        advice.setLogger(logger);
        advice.setFactory(new StopWatchFactory() {
            @Override
            public StopWatch make() {
                return stopWatch;
            }
        });
        when(stopWatch.getTotalTimeMillis()).thenReturn(new Long(1));
    }

    @Test
    public void shouldBeLogDebugMessageOnTraceMethod() throws Exception {
        starter.doBenchmark();
        verify(logger).info("benchmark",
                "public void org.maxur.commons.it.Fake.benchmark(),1");
    }

    @Test
    public void shouldBeLogDebugMessageOnTraceMethodWithReturnValue() throws Exception {
        starter.doBenchmarkWithReturn();
        verify(logger).info("benchmark",
                "public int org.maxur.commons.it.Fake.benchmarkWithReturn(),1");
    }

    @Test
    public void shouldBeLogDebugMessageOnTraceMethodWithArguments() throws Exception {
        starter.doBenchmarkWithArguments();
        verify(logger).info("benchmark",
                "public void org.maxur.commons.it.Fake.benchmarkWithArguments(int),1");
    }

}

