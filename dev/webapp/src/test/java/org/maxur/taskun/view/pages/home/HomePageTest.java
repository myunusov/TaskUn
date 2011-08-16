package org.maxur.taskun.view.pages.home;

import org.apache.wicket.markup.html.WebPage;
import org.junit.Test;
import org.maxur.taskun.view.pages.AbstractPageTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
public class HomePageTest extends AbstractPageTest {

    @Override
    protected Class<? extends WebPage> getPageClass() {
        return HomePage.class;
    }

    @Test
    public void testBasePageComponents() {
        tester.assertComponent("current_panel", CurrentPanel.class);
        tester.assertComponent("total_panel", TotalPanel.class);
    }


}
