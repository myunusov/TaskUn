package org.maxur.taskun;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllIntegrationTests.class,
        AllUnitTests.class
})
public class AllTestSuite {
}
