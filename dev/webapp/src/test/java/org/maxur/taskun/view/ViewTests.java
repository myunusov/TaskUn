package org.maxur.taskun.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.view.model.MenuItemsTest;
import org.maxur.taskun.view.pages.BasePageTest;
import org.maxur.taskun.view.pages.FooterPanelTest;
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
        HomePageTest.class
})
public class ViewTests {
}
