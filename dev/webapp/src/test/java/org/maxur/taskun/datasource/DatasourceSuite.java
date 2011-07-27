package org.maxur.taskun.datasource;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.datasource.hibernate.EmployeeImplTest;
import org.maxur.taskun.datasource.hibernate.EmployeeRepositoryImplTest;
import org.maxur.taskun.datasource.hibernate.EntityRepositoryImplTest;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmployeeRepositoryImplTest.class,
        EmployeeImplTest.class,
        EntityRepositoryImplTest.class
})
public class DatasourceSuite {
}
