package org.maxur.taskun.view.model;

import org.apache.wicket.Page;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The container of Menu Items. It's validate that only one menu item will be selected.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class MenuItems extends ArrayList<MenuItem> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8477491732117084294L;

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param   initialCapacity   the initial capacity of the list
     * @exception IllegalArgumentException if the specified initial capacity
     *            is negative
     */
    public MenuItems(final int initialCapacity) {
        super(initialCapacity);
    }


    /**
     * Calls on select one of meu items. Deselects all other menu items.
     * @param menuItem The selected menu items.
     */
    void setActiveItem(final MenuItem menuItem) {
        final Iterator<MenuItem> iterator = iterator();
        while (iterator.hasNext()) {
            final MenuItem next =  iterator.next();
            if (!next.equals(menuItem)) {
                next.setActive(false);
            }
        }
    }

    /**
     * Adds new menu item to Menu Items container.
     *
     * @param value It's the title of Menu Item.
     * @param targetPage This page will be loaded on select this menu item.
     */
    public void add(final String value, final Class<? extends Page> targetPage) {
        final MenuItem item = MenuItem.create(this, value, targetPage);
        add(item);
    }

    /**
     * Adds new menu item to Menu Items container.
     *
     * @param value It's the title of Menu Item.
     * @param targetPage This page will be loaded on select this menu item.
     * @param active It's true for selected menu items.
     */
    public void add(final String value, final Class<? extends Page> targetPage, final boolean active) {
        final MenuItem item = MenuItem.create(this, value, targetPage);
        if (active) {
            item.setActive(active);
        }
        add(item);
    }

}
