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
    public FooterPanel(final String id) {
        super(id);
        String url = "http://www.maxur.org/";
        ExternalLink link = new ExternalLink("author_url", url);
        link.add(new Label("author_name", "Maxim Yunusov"));
        add(link);
    }
}
