package org.maxur.commons.it;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.service.Logger;
import org.maxur.commons.service.impl.BaseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/23/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"test-aspect-config.xml"})
public class BenchmarkAdviceIT {

    @Autowired
    private Starter starter;

    private static Logger oldLogger;

    @BeforeClass
    public static void setUpAll() throws Exception {
        oldLogger = BaseLogger.instance();
        BaseLogger.setInstance(mock(BaseLogger.class));
    }

    @AfterClass
    public static void tearDownAll() throws Exception {
        BaseLogger.setInstance(oldLogger);
    }


    @Test
    public void shouldBeLogDebugMessageOnTraceMethod() throws Exception {
        starter.doBenchmark();
        verify(BaseLogger.instance()).info("benchmark",
                "Fake.benchmark(),0,StopWatch '': running time (millis) = 0; [Fake.benchmark()] took 0 = 0%");
    }

    @Test
    public void shouldBeLogDebugMessageOnTraceMethodWithReturnValue() throws Exception {
        starter.doBenchmarkWithReturn();
        verify(BaseLogger.instance()).info("benchmark",
                "Fake.benchmarkWithReturn(),0,StopWatch '': running time (millis) = 0; [Fake.benchmark()] took 0 = 0%");
    }

    @Test
    public void shouldBeLogDebugMessageOnTraceMethodWithArguments() throws Exception {
        starter.doBenchmarkWithArguments();
        verify(BaseLogger.instance()).info("benchmark",
                "Fake.benchmarkWithArguments(..),0,StopWatch '': running time (millis) = 0; [Fake.benchmark()] took 0 = 0%");
    }

}

