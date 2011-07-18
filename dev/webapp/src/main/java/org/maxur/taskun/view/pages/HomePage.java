package org.maxur.taskun.view.pages;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.model.EmployeeBean;
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
        final EmployeesGroup group = new EmployeesGroup(controller);
        final EmployeeBean employee = new EmployeeBean(controller);

/*        IModel<EmployeesGroup> groupIModel = new LoadableDetachableModel<EmployeesGroup>() {

            private static final long serialVersionUID = -3408353937500586584L;

            @Override
            protected EmployeesGroup load() {
                return new EmployeesGroup(controller);
            }
        };*/

        AjaxObserver groupObserver = new AjaxObserver();

        IModel<EmployeesGroup> groupModel = new Model<EmployeesGroup>(group);

        final GroupPanel groupPanel = new GroupPanel("group_panel", groupModel, groupObserver);
        groupPanel.setOutputMarkupId(true);
        add(groupPanel);
        final EmployeeListPanel employeeListPanel
                = new EmployeeListPanel("employee_list_panel", groupModel, groupObserver);
        employeeListPanel.setOutputMarkupId(true);
        add(employeeListPanel);

       // groupObserver.addComponents(employeeListPanel);


        add(new EmployeePanel("employee_panel", employee, HomePage.class));

        add(new TaskListPanel("task_list_panel"));



        add(new CurrentUserPanel("current_user_panel", new UserBean()));
    }

}
