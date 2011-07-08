package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * The main menu panel.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
public class MenuPanel extends Panel {

    private static final long serialVersionUID = -1753691216752234935L;

    public MenuPanel(String id) {
        super(id);
        add(new Label("home", "Главная"));
        add(new Label("about", "О программе"));
        add(new Label("blogs", "Блог"));
//        add(new Label("contact", "Контаткы"));
        add(new Label("example", "Пример"));
    }
}
