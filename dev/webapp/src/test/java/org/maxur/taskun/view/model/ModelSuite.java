package org.maxur.taskun.view.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.view.model.employee.EmployeeBeanFactoryTest;
import org.maxur.taskun.view.model.employee.EmployeeBeanTest;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmployeesGroupTest.class,
        MenuItemsTest.class,
        AjaxChangeManagerTest.class,
        BatchCommandTest.class,
        CommandTest.class,
        ModelListTest.class,
        EmployeeBeanTest.class,
        EmployeeBeanFactoryTest.class
})
public class ModelSuite {
}
