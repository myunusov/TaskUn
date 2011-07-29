package org.maxur.taskun.view;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;
import org.maxur.taskun.view.model.MenuItem;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.model.UserBean;

import java.util.Collections;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public final class UserSession extends WebSession {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -1839738545816232994L;

    /**
     * Menu items List as MenuItems class.
     */
    private final MenuItems menuItems;


    /**
     * The System User.
     */
    private UserBean user;

    /**
     * The Constructor of UserSession class.
     *
     * @param request   The Base class for page request.
     * @param items The menu items
     */
    public UserSession(
            final Request request,
            final MenuItems items
    ) {
        super(request);
        this.menuItems = items;

    }

    /**
     * Gets Menu Items.
     *
     * @return The Menu Items List.
     */
    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(menuItems);
    }

    /**
     * Gets Current User.
     * @return The current User.
     */
    public UserBean getUser() {
        return user;
    }

    /**
     * Set Current User.
     * @param user The current User
     */
    public void setUser(final UserBean user) {
        this.user = user;
    }
}
