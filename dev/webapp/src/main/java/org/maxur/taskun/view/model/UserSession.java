package org.maxur.taskun.view.model;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.ApplicationController;

import java.util.Collections;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class UserSession extends WebSession {

    /**
     * The ApplicationController bean.
     */
    private final transient ApplicationController applicationController;

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
     * @param request   The Base class for page request.
     * @param controller The ApplicationController bean.
     * @param menuItems The menu items
     */
    public UserSession(
            final Request request,
            final ApplicationController controller,
            final MenuItems menuItems
    ) {
        super(request);
        this.applicationController = controller;
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

    /**
     * Returns all employee from repository.
     * @return  The employees List from repository
     */
    public List<Employee> getAllEmployee() {
        return applicationController.getAllEmployee();
    }

    /**
     * Save employee into repository.
     * @param employee The saving employee.
     */
    public void saveEmployee(final Employee employee) {
        applicationController.saveEmployee(employee);
    }
}
