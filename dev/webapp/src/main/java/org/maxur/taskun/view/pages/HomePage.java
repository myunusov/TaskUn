package org.maxur.taskun.view.pages;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.components.AjaxChangeManager;
import org.maxur.taskun.view.model.EmployeesGroup;
import org.maxur.taskun.view.model.UserBean;

/**
 * The Home Page Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class HomePage extends BasePage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 1773451707947074585L;

    /**
     * The ApplicationController bean. It's injected by Wicket IoC.
     */
    @SpringBean
    private ApplicationController controller;


    /**
     * Constructs HomePage instance.
     */
    public HomePage() {
        final AjaxChangeManager groupChangeManager = new AjaxChangeManager();
        final EmployeesGroup group = new EmployeesGroup(controller);
        final IModel<EmployeesGroup> groupModel = new Model<EmployeesGroup>() {
            @Override
            public EmployeesGroup getObject() {
                return group;
            }
        };
        EmployeeWindow employeeWindow = new EmployeeWindow("create_dialog", group, groupChangeManager);
        add(employeeWindow);

        add(new GroupPanel("group_panel", groupModel, employeeWindow, groupChangeManager));
        add(new EmployeeListPanel("employee_list_panel", groupModel, employeeWindow, groupChangeManager));
        add(new TaskListPanel("task_list_panel"));
        add(new CurrentUserPanel("current_user_panel", new UserBean()));

    }
}
