package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.components.MenuItemsView;
import org.maxur.taskun.view.model.MenuItem;
import org.maxur.taskun.view.state.UserSession;

import java.util.List;

/**
 * The main menu panel.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
public class MenuPanel extends Panel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -1753691216752234935L;


    /**
     * The panel's constructor.
     *
     * @param id The panel's identifier.
     */
    public MenuPanel(final String id) {
        super(id);
        final ListView listView = new MenuItemsView("menu_items", getMenuItems());
        add(listView);
    }

    /**
     * Get The User Session.
     *
     * @return The User Session.
     */
    public UserSession getUserSession() {
        return (UserSession) getSession();
    }

    /**
     * Get Menu Items.
     *
     * @return The Menu Items List.
     */
    public final List<MenuItem> getMenuItems() {
        return getUserSession().getMenuItems();
    }

}
