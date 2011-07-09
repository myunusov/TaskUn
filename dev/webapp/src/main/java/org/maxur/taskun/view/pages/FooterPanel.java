package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
public class FooterPanel extends Panel {

     /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5905374749075989810L;

    /**
     * The panel's constructor.
     * @param id The panel's identifier.
     */
    //TODO MY must be exclude string constants
    public FooterPanel(final String id) {
        super(id);
        ExternalLink link = new ExternalLink("author_url", "http://www.maxur.org/");
        link.add(new Label("author_name", "Maxim Yunusov"));
        add(link);
    }
}
