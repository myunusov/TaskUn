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
public class HeaderPanelTest {

    private WicketTester tester;

    @Before
    public void setUp() {
        tester = new WicketTester();
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(String panelId) {
                return new HeaderPanel(panelId);
            }
        });
    }

    @Test
    public void testAppNameLabel() throws Exception {
        tester.assertLabel("panel:app_name", "ТаскУН");
    }

    @Test
    public void testAppDescriptionLabel() throws Exception {
        tester.assertLabel("panel:app_desc", "простое управление задачами");
    }

    @Test
    public void testWicketPanel() throws Exception {
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

}