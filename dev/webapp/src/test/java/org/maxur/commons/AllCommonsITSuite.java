package org.maxur.commons;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.commons.it.BenchmarkAdviceIT;
import org.maxur.commons.it.TraceAdviceIT;

@RunWith(Suite.class)@Suite.SuiteClasses({
        BenchmarkAdviceIT.class,
        TraceAdviceIT.class
})
public class AllCommonsITSuite {
}
