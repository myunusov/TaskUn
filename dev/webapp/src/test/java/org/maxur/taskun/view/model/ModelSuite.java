package org.maxur.taskun.view.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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
        CommandTest.class
})
public class ModelSuite {
}
