package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/15/11
 */
public abstract class AbstractPanelTest extends AbstractTest {

    @Override
    protected void start() {
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(final String panelId) {
                return makeTestPanel(panelId);
            }
        });
    }

    protected abstract Panel makeTestPanel(String panelId);
}
