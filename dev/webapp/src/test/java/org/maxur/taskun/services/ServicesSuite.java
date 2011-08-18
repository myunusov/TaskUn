package org.maxur.taskun.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.services.Impl.NotifierImplTest;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        ApplicationControllerImplTest.class,
        NotifierImplTest.class
})
public class ServicesSuite {
}
