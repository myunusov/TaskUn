package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import java.io.Serializable;

/**
 * Dummy Link without behavior.
 *
 * @param <E> type of model object
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public final class Mark<E extends Serializable> extends Link<E> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2836637384680550660L;

    /**
     * Construct instance of Mark.
     * @param id The Mark id
     */
    public Mark(final String id) {
        super(id);
    }

    public Mark(String id, E object) {
        super(id, new Model<E>(object));
    }

    /**
     *  Fake method without behaviour.
     */
    @Override
    public void onClick() {
    }
}
