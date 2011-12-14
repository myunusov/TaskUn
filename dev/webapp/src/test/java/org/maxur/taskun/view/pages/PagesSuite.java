package org.maxur.taskun.view.pages;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.view.pages.admin.AdminPagesSuite;
import org.maxur.taskun.view.pages.archive.ArchivePagesSuite;
import org.maxur.taskun.view.pages.employee.EmployeePagesSuite;
import org.maxur.taskun.view.pages.home.HomePagesSuite;
import org.maxur.taskun.view.pages.self.SelfPagesSuite;
import org.maxur.taskun.view.pages.task.TaskPagesSuite;
import org.maxur.taskun.view.pages.user.UserPagesSuite;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        BasePageTest.class,
        HeaderPanelTest.class,
        FooterPanelTest.class,
        MenuPanelTest.class,
        HomePagesSuite.class,
        EmployeePagesSuite.class,
        AdminPagesSuite.class,
        ArchivePagesSuite.class,
        SelfPagesSuite.class,
        TaskPagesSuite.class,
        UserPagesSuite.class
})
public class PagesSuite {
}
