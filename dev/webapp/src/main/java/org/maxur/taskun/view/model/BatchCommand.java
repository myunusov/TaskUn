package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * The composite of commands class instances.
 * GoF Composite pattern.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class BatchCommand<E extends Bean> extends Command<E> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -3367883906524236795L;

    /**
     * The commands Repository.
     */
    private final Command<E>[] commands;

    /**
     * Constructs batch command from commands list.
     *
     * @param commands The commands list.
     */
    public BatchCommand(Command<E>... commands) {
        this.commands = commands;
    }

    /**
     * Executes batch command. So all commands from this batch will be executed.
     *
     * @param target
     * @param model
     * @see org.maxur.taskun.view.model.Command#execute(org.apache.wicket.ajax.AjaxRequestTarget,
     *      org.maxur.taskun.view.model.Bean)
     */
    @Override
    public void execute(AjaxRequestTarget target, E model) {
        for (Command<E> command : commands) {
            command.execute(target, model);
        }
    }
}
