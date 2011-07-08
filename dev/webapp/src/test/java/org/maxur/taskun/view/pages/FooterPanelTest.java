package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/8/11
 */
public class FooterPanelTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester();
        tester.startPanel(new ITestPanelSource() {
            private static final long serialVersionUID = -2267672189100332393L;

            @Override
            public Panel getTestPanel(String panelId) {
                return new FooterPanel(panelId);
            }
        });
    }

    @Test
    public void testAppNameLabel() throws Exception {
        tester.assertLabel("panel:author_name", "Maxim Yunusov");
    }

}
