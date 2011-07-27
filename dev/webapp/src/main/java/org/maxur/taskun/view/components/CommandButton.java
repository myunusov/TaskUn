package org.maxur.taskun.view.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.model.Bean;
import org.maxur.taskun.view.model.Command;

/**
* @author Maxim Yunusov
* @version 1.0 7/22/11
*/
public class CommandButton<T extends Bean> extends AjaxButton {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -381155212869784274L;

    private final Command<T> command;

    private final T model;

    public CommandButton(
            final String id,
            final Form<?> form,
            final Command<T> command,
            final T model,
            final IModel<String> value
    ) {
        super(id, value, form);
        this.command = command;
        this.model = model;
    }

    @Override
    protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
        command.execute(target, model);
    }
}
