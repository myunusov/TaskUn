package org.maxur.taskun.it;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>11.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        EmployeeServiceIT.class,
        EmployeeTransactionIT.class,
        AbstractEmployeeWithValidatorsIT.class,
        EmployeeBuilderWithValidatorsIT.class,
        ValidatorIT.class
})
public class AllTaskunITSuite {
}
