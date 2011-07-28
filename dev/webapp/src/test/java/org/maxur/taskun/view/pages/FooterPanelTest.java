package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.link.ExternalLink;
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
            @Override
            public Panel getTestPanel(String panelId) {
                return new FooterPanel(panelId);
            }
        });
    }

    @Test
    public void testAppURLLink() throws Exception {
        tester.assertComponent("panel:author_url", ExternalLink.class);
    }

    @Test
    public void testWicketPanel() throws Exception {
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

}
