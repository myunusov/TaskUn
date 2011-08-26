package org.maxur.taskun.datasource.hibernate.employee;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.maxur.taskun.datasource.DataSourceNotifier;
import org.maxur.taskun.domain.employee.BaseEmployeeTest;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/25/11
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeBuilderImplTest extends BaseEmployeeTest {

    @Mock private DataSourceNotifier notifier;

    @Before
    public void setUp() throws Exception {
        final EmployeeBuilderImpl employeeBuilder = new EmployeeBuilderImpl();
        employeeBuilder.setNotifier(notifier);
        builder = employeeBuilder;
    }


}
