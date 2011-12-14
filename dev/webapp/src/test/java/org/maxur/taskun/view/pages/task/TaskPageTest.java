package org.maxur.taskun.view.pages.task;

import org.apache.wicket.markup.html.WebPage;
import org.junit.Test;
import org.maxur.taskun.view.pages.AbstractPageTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/17/11
 */
public class TaskPageTest extends AbstractPageTest {

    @Override
    protected Class<? extends WebPage> getPageClass() {
        return TaskPage.class;
    }

    @Test
    public void testTaskListPanel() throws Exception {
        tester.assertComponent("task_list_panel", TaskListPanel.class);
    }

}
