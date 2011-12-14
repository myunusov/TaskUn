package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Test;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/8/11
 */
public class FooterPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new FooterPanel(panelId);
    }

    @Test
    public void testAppURLLink() throws Exception {
        tester.assertComponent("panel:author_url", ExternalLink.class);
    }


}
