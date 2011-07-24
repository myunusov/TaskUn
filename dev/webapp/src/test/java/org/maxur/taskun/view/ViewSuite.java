package org.maxur.taskun.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.view.commands.CommandsSuite;
import org.maxur.taskun.view.model.ModelSuite;
import org.maxur.taskun.view.pages.BasePageTest;
import org.maxur.taskun.view.pages.CurrentUserPanelTest;
import org.maxur.taskun.view.pages.FooterPanelTest;
import org.maxur.taskun.view.pages.HeaderPanelTest;
import org.maxur.taskun.view.pages.MenuPanelTest;
import org.maxur.taskun.view.pages.employee.EmployeePagesSuite;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        ModelSuite.class,
        CommandsSuite.class,
        EmployeePagesSuite.class,
        TaskunApplicationTest.class,
        BasePageTest.class,
        HeaderPanelTest.class,
        FooterPanelTest.class,
        CurrentUserPanelTest.class,
        MenuPanelTest.class
})
public class ViewSuite {
}
