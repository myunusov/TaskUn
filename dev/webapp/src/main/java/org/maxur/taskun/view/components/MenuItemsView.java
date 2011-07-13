package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.maxur.taskun.view.model.MenuItem;

import java.util.List;

/**
 * The class is Wicket View of menu items.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class MenuItemsView extends ListView<MenuItem> {

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
        final Mark<?> mark = new Mark<Object>();
        link.add(mark);
        mark.add(new Label("menu_item_name", item.getTitle()));
    }

}
