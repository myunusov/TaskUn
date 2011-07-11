package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.view.components.EmployeeForm;
import org.maxur.taskun.view.components.EmployeesView;
import org.maxur.taskun.view.state.UserSession;

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
     * Get The User Session.
     *
     * @return The User Session.
     */
    public UserSession getUserSession() {
        return (UserSession) getSession();
    }

    public HomePage() {
        super();
        add(new FeedbackPanel("feedback"));
        add(new EmployeeForm("employees_form", getUserSession(), HomePage.class));
        final ListView<Employee> listView = new EmployeesView("employees", getUserSession().getAllEmployee());
        add(listView);
    }

}
