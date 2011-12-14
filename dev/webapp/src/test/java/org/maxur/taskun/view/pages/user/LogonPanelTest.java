package org.maxur.taskun.view.pages.user;

import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.model.UserBean;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/17/11
 */
public class LogonPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new LogonPanel(panelId, new UserBean(), null, null);
    }
}
