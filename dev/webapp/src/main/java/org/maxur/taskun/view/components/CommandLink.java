package org.maxur.taskun.view.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.maxur.taskun.view.model.Bean;
import org.maxur.taskun.view.model.BeanFactory;
import org.maxur.taskun.view.model.Command;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class CommandLink<T extends Bean> extends AjaxFallbackLink {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8095364080994940288L;

    private final T model;

    private final BeanFactory<T> factory;

    private Command<T> command;

    public CommandLink(
            final String id,
            final T model,
            final Command<T> command
    ) {
        super(id);
        this.factory = null;
        this.model = model;
        this.command = command;
    }

    public CommandLink(
            final String id,
            final BeanFactory<T> factory,
            final Command<T> command
    ) {
        super(id);
        this.factory = factory;
        this.model = null;
        this.command = command;
    }

    @SuppressWarnings("unchecked")
	@Override
    public void onClick(final AjaxRequestTarget target) {
        if (null != model) {
            command.execute(target, model);
        } else if (null != factory) {
            command.execute(target, factory);
        }
    }
}
