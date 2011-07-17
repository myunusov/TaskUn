package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.view.model.UserBean;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class CurrentUserPanelTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester();
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(String panelId) {
                return new CurrentUserPanel(panelId, new UserBean());
            }
        });
    }

    @Test
    public void testPanelTitle() throws Exception {
        tester.assertComponent("panel:current_user", Label.class);
        tester.assertLabel("panel:current_user", "Кто Вы ?");
    }

}
