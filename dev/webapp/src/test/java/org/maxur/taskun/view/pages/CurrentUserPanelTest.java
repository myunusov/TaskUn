package org.maxur.taskun.view.pages;

import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Test;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.pages.user.CurrentUserPanel;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class CurrentUserPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(final String panelId) {
        final CommandRepositoryImpl dummyCommandsRepository = new CommandRepositoryImpl();
        return new CurrentUserPanel(panelId, dummyCommandsRepository);
    }

    @Test
    public void testPanelTitle() throws Exception {
        tester.assertComponent("panel:current_user", Label.class);
        tester.assertLabel("panel:current_user", "About Self");
    }

    @Test
    public void testUnknownLabel() throws Exception {
        tester.assertComponent("panel:unknown", Label.class);
        tester.assertLabel("panel:unknown", "Anonymous");
    }

    @Test
    public void testButton() throws Exception {
        tester.assertComponent("panel:logon_link", AjaxFallbackLink.class);
    }

}
