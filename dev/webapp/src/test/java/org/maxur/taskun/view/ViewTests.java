package org.maxur.taskun.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.view.model.EmployeesGroupTest;
import org.maxur.taskun.view.model.MenuItemsTest;
import org.maxur.taskun.view.pages.BasePageTest;
import org.maxur.taskun.view.pages.CurrentUserPanelTest;
import org.maxur.taskun.view.pages.EmployeeListPanelTest;
import org.maxur.taskun.view.pages.EmployeePanelTest;
import org.maxur.taskun.view.pages.FooterPanelTest;
import org.maxur.taskun.view.pages.GroupPanelTest;
import org.maxur.taskun.view.pages.HeaderPanelTest;
import org.maxur.taskun.view.pages.HomePageTest;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        TaskunApplicationTest.class,
        MenuItemsTest.class,
        BasePageTest.class,
        HeaderPanelTest.class,
        FooterPanelTest.class,
        EmployeePanelTest.class,
        HomePageTest.class,
        EmployeesGroupTest.class,
        EmployeeListPanelTest.class,
        CurrentUserPanelTest.class,
        GroupPanelTest.class
})
public class ViewTests {
}
