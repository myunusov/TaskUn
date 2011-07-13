package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.view.components.EmployeeForm;
import org.maxur.taskun.view.components.EmployeesView;
import org.maxur.taskun.view.model.UserSession;

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
     *  Constructs HomePage instance.
     */
    public HomePage() {
        super();
        add(new FeedbackPanel("feedback"));
        add(new Label("employees_title", "Коллеги"));
        add(new EmployeeForm("employees_form", getUserSession(), HomePage.class));
        final ListView<Employee> listView = new EmployeesView("employees", getUserSession().getAllEmployee());
        add(new Label("current_user", "Кто Вы ?"));
        add(listView);
    }


    /**
     * Get The User Session.
     *
     * @return The User Session.
     */
    public final UserSession getUserSession() {
        return (UserSession) getSession();
    }

}
