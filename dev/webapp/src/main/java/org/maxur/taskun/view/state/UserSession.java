package org.maxur.taskun.view.state;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.model.MenuItem;
import org.maxur.taskun.view.model.MenuItems;

import java.util.Collections;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class UserSession extends WebSession {

    private transient ApplicationController controller;

    public void setController(final ApplicationController controller) {
        this.controller = controller;
    }

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -1839738545816232994L;

    /**
     * Menu items List as MenuItems class.
     */
    private final MenuItems items;

    /**
     * The Constructor of UserSession class.
     *
     * @param request The Base class for page request.
     * @param menuItems The menu items
     */
    public UserSession(final Request request, final MenuItems menuItems) {
        super(request);
        this.items = menuItems;
    }

    /**
     * Get Menu Items.
     *
     * @return The Menu Items List.
     */
    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(items);
    }


    public List<Employee> getAllEmployee() {
        return controller.getAllEmployee();
    }

    public void saveEmployee(final Employee employee) {
       controller.saveEmployee(employee);
    }
}
