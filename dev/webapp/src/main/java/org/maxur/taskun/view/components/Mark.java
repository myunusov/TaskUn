package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.link.Link;

/**
 * Dummy Link without behavior.
 *
 * @param <E> type of model object
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public final class Mark<E> extends Link<E> {

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

    /**
     *  Fake method without behaviour.
     */
    @Override
    public void onClick() {
    }
}
