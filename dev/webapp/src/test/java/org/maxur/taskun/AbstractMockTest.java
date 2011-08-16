package org.maxur.taskun;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
@RunWith(JMock.class)
public abstract class AbstractMockTest {

    protected Mockery context;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
    }

}
