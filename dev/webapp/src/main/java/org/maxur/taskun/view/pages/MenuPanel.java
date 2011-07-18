package org.maxur.taskun.view.pages;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.maxur.taskun.view.UserSession;
import org.maxur.taskun.view.components.Mark;
import org.maxur.taskun.view.model.MenuItem;

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
        final ListView<MenuItem> listView = new MenuItemsView("menu_items", getMenuItems());
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

    /**
     * The class is Wicket View of menu items.
     */
    public static class MenuItemsView extends ListView<MenuItem> {

        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = 8835336241872539442L;

        /**
         * Constructs new MenuItemsView instance.
         *
         * @param id        The MenuItemsView
         * @param menuItems The Menu items List for represent on web.
         */
        public MenuItemsView(final String id, final List<MenuItem> menuItems) {
            super(id, menuItems);
        }

        /**
         * Populate a given menu items item.
         * @see org.apache.wicket.markup.html.list.ListView#populateItem(
         *                                org.apache.wicket.markup.html.list.ListItem)
         *
         * @param listItem The item to populate
         */
        @Override
        protected void populateItem(final ListItem<MenuItem> listItem) {
            MenuItem item = listItem.getModelObject();
            final Link<MenuItem> link = new MenuItemLink(item);
            listItem.add(link);
            final Mark<?> mark = new Mark<Object>("menu_item_link");
            link.add(mark);
            mark.add(new Label("menu_item_name", item.getTitle()));
        }

        /**
         * Menu item Link component.
         */
        static class MenuItemLink extends Link<MenuItem> {

            /**
             * Serial Version UID.
             */
            private static final long serialVersionUID = -899628430579241468L;

            /**
             * Constructs the MenuItemLink instance.
             * @param item The Menu Item descriptor.
             */
            public MenuItemLink(final MenuItem item) {
                super("menu_item", new Model<MenuItem>(item));
                if (item.isActive()) {
                    add(new SimpleAttributeModifier("class", "current_page_item"));
                }
            }

            @Override
            /**
             * Is called on Click.
             */
            public void onClick() {
                MenuItem item = getModelObject();
                setResponsePage(item.getTargetPage());
                item.setActive(true);
            }
        }
    }
}
