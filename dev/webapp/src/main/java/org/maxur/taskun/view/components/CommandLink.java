package org.maxur.taskun.view.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.CommandRepository;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class CommandLink<T> extends AjaxFallbackLink<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8095364080994940288L;

    private final CommandRepository commands;

    private final String commandId;

    public CommandLink(
            final String id,
            final IModel<T> model,
            final CommandRepository commands,
            final String commandId
    ) {
        super(id, model);
        this.commands = commands;
        this.commandId = commandId;
    }

    @Override
    public void onClick(final AjaxRequestTarget target) {
        final Command<T> command = commands.get(commandId);
        command.execute(target, getModel());
    }
}
