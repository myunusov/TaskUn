package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

/*
 * Panel for displaying the contents of a application header.
 *
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
     *
     * @param id The panel's identifier.
     */
    public HeaderPanel(final String id) {
        super(id);
        add(new Label("app_name", new ResourceModel("application.name")));
        add(new Label("app_desc", new ResourceModel("application.desc")));
    }
}
