package org.maxur.taskun.view.pages;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import java.util.ArrayList;
import java.util.Collections;
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
        add(new ListView("menu_items", getMenuItems()) {

            private static final long serialVersionUID = -2596647392896422489L;

            @Override
            protected void populateItem(ListItem listItem) {
                MenuItem item = (MenuItem) listItem.getModelObject();
                final Link link = new Link("menu_item") {

                    @Override
                    public void onClick() {

                    }
                };
                if (item.isActive()) {
                    link.add(new SimpleAttributeModifier("class", "current_page_item"));
                }
                listItem.add(link);
                final Link link1 = new Link("menu_item_link") {
                    @Override
                    public void onClick() {
                    }
                };
                link.add(link1);
                link1.add(new Label("menu_item_name", item.getValue()));
            }
        });
//        add(new Label("home", "Главная"));
//        add(new Label("about", "О программе"));
//        add(new Label("blogs", "Блог"));
//        add(new Label("contact", "Контаткы"));
//        add(new Label("example", "Пример"));
    }

    private List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem("Главная", true));
        items.add(new MenuItem("Блог"));
        items.add(new MenuItem("О программе"));
        return Collections.unmodifiableList(items);
    }

    private class MenuItem {

        private final String value;
        private final boolean active;

        public MenuItem(final String value) {
            this.value = value;
            this.active = false;
        }

        public MenuItem(final String value, final boolean active) {
            this.value = value;
            this.active = active;
        }

        public String getValue() {
            return value;
        }

        public boolean isActive() {
            return active;
        }
    }
}
