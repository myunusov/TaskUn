package org.maxur.taskun.view.pages.employee;

import org.maxur.commons.annotation.Benchmark;
import org.maxur.taskun.view.commands.EmployeeRemoveCommand;
import org.maxur.taskun.view.commands.EmployeeSelectCommand;
import org.maxur.taskun.view.commands.EmployeeSubmitCommand;
import org.maxur.taskun.view.commands.ShowModalWindowCommand;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.BasePage;

/**
 * The Home Page Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class EmployeePage extends BasePage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 1773451707947074585L;


    /**
     * Constructs EmployeePage instance.
     */
    public EmployeePage() {
        super();
        createPage();
    }

    @Benchmark
    private void createPage() {
        final EmployeesGroup group = new EmployeesGroup();

        final EmployeeWindow employeeWindow = new EmployeeWindow("employee_window", getCommands());
        add(employeeWindow);

        add(new GroupPanel("group_panel", group, getCommands()));
        add(new EmployeeListPanel("employee_list_panel", group, getCommands()));

        getCommands().persist("employee.edit", new ShowModalWindowCommand<EmployeeBean>(employeeWindow));
        getCommands().persist("employee.select", new EmployeeSelectCommand());
        getCommands().persist("employee.submit", new EmployeeSubmitCommand());
        getCommands().persist("employee.remove", new EmployeeRemoveCommand());
    }

}
