package org.maxur.taskun.datasource;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.datasource.hibernate.EmployeeRepositoryImplTest;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmployeeRepositoryImplTest.class
})
public class DatasourceTests {
}
