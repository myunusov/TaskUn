package org.maxur.taskun.view.pages.home;

import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
public class CurrentPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new CurrentPanel(panelId);
    }
}
