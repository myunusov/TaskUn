package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

/**
 * The composite of commands class instances.
 * GoF Composite pattern.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class BatchCommand<T> extends Command<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -3367883906524236795L;

    /**
     * The commands Repository.
     */
    private final Command<T>[] commands;

    /**
     * Constructs batch command from commands list.
     * @param commands The commands list.
     */
    public BatchCommand(Command<T>... commands) {
        this.commands = commands;
    }

    /**
     * Executes batch command. So all commands from this batch will be executed.
     * @param target
     * @param model
     */
    @Override
    public void execute(final AjaxRequestTarget target, final IModel<T> model) {
        for (Command<T> command : commands) {
            command.execute(target, model);
        }
    }

}
