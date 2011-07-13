package org.maxur.taskun.view.model;

import org.apache.wicket.Page;

import java.io.Serializable;

/**
 * The Menu Item.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public final class MenuItem implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5997444871127787712L;

    /**
     * The Menu Items container.
     */
    private final MenuItems owner;

    /**
     * The title of Menu Item.
     */
    private final String title;

    /**
     * It's true if menu item in selected state.
     */
    private boolean active;

    /**
     * The target page. This page will be loaded on select this menu item.
     */
    private final Class<? extends Page> targetPage;

    /**
     * Constructs Menu Item.
     * @param items The Menu Items container. This bidirectional link.
     * @param value The title of Menu Item.
     * @param page The target page. This page will be loaded on select this menu item.
     */
    private MenuItem(
            final MenuItems items,
            final String value,
            final Class<? extends Page> page
    ) {
        this.owner = items;
        this.title = value;
        this.targetPage = page;
        this.active = false;
    }

    /**
     * Set Menu Item active and if it's true than deactivate all other Menu Items from container.
     * @param value It's true if menu item in selected state.
     */
    public void setActive(final boolean value) {
        this.active = value;
        if (value) {
            owner.setActiveItem(this);
        }
    }

    /**
     * Getter for value field.
     * @return The value of value field. It's the title of Menu Item.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for active field.
     * @return The value of active field. It's true if menu item in selected state.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Getter for targetPage field.
     * @return  The value of targetPage field. This page will be loaded on select this menu item.
     */
    public Class<? extends Page> getTargetPage() {
        return targetPage;
    }

    /**
     * The Factory method for construct MenuItem instance.
     * @param items The Menu Items container.
     * @param value It's the title of Menu Item.
     * @param targetPage This page will be loaded on select this menu item.
     * @return  The MenuItem instance.
     */
    static MenuItem create(
            final MenuItems items,
            final String value,
            final Class<? extends Page> targetPage
    ) {
        return new MenuItem(items, value, targetPage);
    }

}
