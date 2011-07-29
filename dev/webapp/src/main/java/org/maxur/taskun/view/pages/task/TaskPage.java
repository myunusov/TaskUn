package org.maxur.taskun.view.pages.task;

import org.maxur.taskun.view.pages.BasePage;

/**
 * The Home Page Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class TaskPage extends BasePage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 1773451707947074585L;


    /**
     * Constructs EmployeePage instance.
     */
    public TaskPage() {
        super();
        add(new TaskListPanel("task_list_panel"));
    }

}
