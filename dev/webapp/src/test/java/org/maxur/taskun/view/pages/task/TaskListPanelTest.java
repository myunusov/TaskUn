package org.maxur.taskun.view.pages.task;

import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/17/11
 */
public class TaskListPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new TaskListPanel(panelId);
    }
}
