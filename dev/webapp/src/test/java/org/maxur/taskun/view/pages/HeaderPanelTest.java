package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/8/11
 */
public class HeaderPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new HeaderPanel(panelId);
    }

}