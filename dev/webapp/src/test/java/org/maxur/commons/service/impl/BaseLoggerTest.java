package org.maxur.commons.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.service.Logger;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/21/11
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseLoggerTest {

    private Logger logger;

    private Logger fake;

    @Mock
    private org.slf4j.Logger log;

    @Before
    public void setUp() throws Exception {
        logger = BaseLogger.instance();
        fake = new BaseLogger() {
            @Override
            protected org.slf4j.Logger createLogger(String name) {
                return log;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        BaseLogger.setInstance(logger);
    }

    @Test
    public void shouldBeSingleton() throws Exception {
        assertTrue("BaseLogger Should Be Singleton", logger == BaseLogger.instance());
    }

    @Test
    public void shouldBeSupportInheritance() throws Exception {
        Logger newLogger = mock(Logger.class);
        BaseLogger.setInstance(newLogger);
        assertTrue("BaseLogger Should Be Support Inheritance", newLogger == BaseLogger.instance());
    }


    @Test
    public void shouldBeLogDebugMessageForStringSource() throws Exception {
        final String msg = "MessageA";
        fake.debug("A", msg);
        verify(log).debug(msg);
    }

    @Test
    public void shouldBeLogDebugMessageForClassSource() throws Exception {
        final String msg = "MessageB";
        fake.debug(Object.class, msg);
        verify(log).debug(msg);
    }


    @Test
    public void shouldBeLogInfoMessageForStringSource() throws Exception {
        final String msg = "MessageC";
        fake.info("C", msg);
        verify(log).info(msg);
    }

    @Test
    public void shouldBeCreateLoggerAsSLF4J() throws Exception {
        BaseLogger fake1 = new BaseLogger() {
            @Override
            public org.slf4j.Logger createLogger(String name) {
                return super.createLogger(name);
            }
        };
        assertNotNull("BaseLogger should Be Create inner Logger As SLF4J", fake1.createLogger("A"));
    }

     @Test
    public void shouldBeReuseCreatedLogger() throws Exception {
        BaseLogger fake1 = new BaseLogger() {
            @Override
            public org.slf4j.Logger createLogger(String name) {
                return log;
            }
        };
        fake1.info("A", "M");
        fake1.info("A", "M");
        verify(log, times(2)).info("M");
    }

    @Test
    @Ignore
    public void shouldBeLogInfoMessageForClassSource() throws Exception {
        final String msg = "MessageD";
        fake.debug(Object.class, msg);
        verify(log).info(msg);
    }


}
