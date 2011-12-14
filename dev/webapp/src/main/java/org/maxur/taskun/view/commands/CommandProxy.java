package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.view.model.Bean;
import org.maxur.taskun.view.model.BeanFactory;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.CommandRepository;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/29/11
 */
public class CommandProxy<T  extends Bean> extends Command<T> {

    private static final long serialVersionUID = -3589746401000265642L;

    private final CommandRepository commands;

    private final String id;

    public CommandProxy(final CommandRepository commands, final String id) {
        this.commands = commands;
        this.id = id;
    }

    @Override
    public void execute(final AjaxRequestTarget target, final T model) {
        final Command<T> command = commands.get(id);
        command.execute(target, model);
    }

    public void execute(AjaxRequestTarget target, BeanFactory<T> factory) {
        final Command<T> command = commands.get(id);
        command.execute(target, factory);
    }

}
