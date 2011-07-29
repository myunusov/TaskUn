package org.maxur.taskun.view.commands;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EmployeeSelectCommandTest.class,
        EmployeeRemoveCommandTest.class,
        ShowModalWindowCommandTest.class,
        EmployeeSubmitCommandTest.class,
        CommandRepositoryImplTest.class
})
public class CommandsSuite {
}
