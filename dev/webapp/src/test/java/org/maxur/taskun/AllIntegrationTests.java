package org.maxur.taskun;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.it.EmployeeServiceIT;
import org.maxur.taskun.it.EmployeeTransactionIT;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>11.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        EmployeeServiceIT.class,
        EmployeeTransactionIT.class
})
public class AllIntegrationTests {
}
