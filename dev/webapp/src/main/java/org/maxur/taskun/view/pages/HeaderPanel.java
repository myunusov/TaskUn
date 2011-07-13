package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
public class HeaderPanel extends Panel {

     /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -9046096152520510321L;

    /**
     * The panel's constructor.
     * @param id The panel's identifier.
     */
    public HeaderPanel(final String id) {
        //TODO MY must be exclude string constants
        super(id);
        add(new Label("app_name", "ТаскУН"));
        add(new Label("app_desc", "простое управление задачами"));
    }
}
