package org.maxur.taskun.view.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.view.pages.admin.AdminPagesSuite;
import org.maxur.taskun.view.pages.employee.EmployeePagesSuite;
import org.maxur.taskun.view.pages.home.HomePagesSuite;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        BasePageTest.class,
        HeaderPanelTest.class,
        FooterPanelTest.class,
        CurrentUserPanelTest.class,
        MenuPanelTest.class,
        HomePagesSuite.class,
        EmployeePagesSuite.class,
        AdminPagesSuite.class
})
public class PagesSuite {
}
