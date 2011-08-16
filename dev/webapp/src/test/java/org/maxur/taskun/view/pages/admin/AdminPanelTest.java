package org.maxur.taskun.view.pages.admin;

import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
public class AdminPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        final CommandRepositoryImpl dummyCommandRepository = new CommandRepositoryImpl();
        return new AdminPanel(panelId, dummyCommandRepository);
    }
}
