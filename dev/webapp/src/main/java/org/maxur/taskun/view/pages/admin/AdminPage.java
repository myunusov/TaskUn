package org.maxur.taskun.view.pages.admin;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.BasePage;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/29/11
 */
@AuthorizeInstantiation("ROLE_ADMIN")
public class AdminPage extends BasePage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -1953311654388622649L;

    /**
     * Constructs AdminPage instance.
     */
    public AdminPage() {
        super();
        final EmployeesGroup group = new EmployeesGroup();
        add(new UserPanel("user_panel", group, getCommands()));
        add(new AdminPanel("admin_panel", getCommands()));
    }

}