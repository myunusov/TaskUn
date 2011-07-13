package org.maxur.taskun.view.model;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import java.util.Collections;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class UserSession extends WebSession {

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
     * @param menuItems The menu items
     */
    public UserSession(
            final Request request,
            final MenuItems menuItems
    ) {
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

}
