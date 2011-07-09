package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.link.Link;

/**
 * Dummy Link without behaviour.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
final class Mark extends Link {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2836637384680550660L;

    /**
     * Construct instance of Mark.
     */
    public Mark() {
        super("menu_item_link");
    }

    @Override
    /**
     *  Fake method without behaviour.
     */
    public void onClick() {
    }
}
