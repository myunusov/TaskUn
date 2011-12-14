package org.maxur.taskun;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.commons.AllCommonsSuite;
import org.maxur.taskun.it.AllTaskunITSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AllTaskunITSuite.class,
        AllTaskunSuite.class,
        AllCommonsSuite.class
})
public class AllSuite {
}
