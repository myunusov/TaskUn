package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;

/**
 * Panel for displaying the contents of a application footer.
 *
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
        add(new ExternalLink("author_url", new ResourceModel("url.author")));
    }
}
