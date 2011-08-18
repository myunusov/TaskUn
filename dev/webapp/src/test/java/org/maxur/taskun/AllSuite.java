package org.maxur.taskun;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.commons.domain.CommonsDomainSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllITSuite.class,
        AllUnitSuite.class,
        CommonsDomainSuite.class
})
public class AllSuite {
}
