package org.maxur.taskun.view.components;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.maxur.taskun.view.model.MenuItem;

/**
* @author Maxim Yunusov
* @version 1.0 7/9/11
*/
class MenuItemLink extends Link<MenuItem> {

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
