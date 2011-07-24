package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public abstract class Command<T> implements Serializable, Cloneable {

    private static final long serialVersionUID = 6116373389784514896L;

    public abstract void execute(AjaxRequestTarget target, IModel<T> model);

    @Override
    public Command<T> clone() throws CloneNotSupportedException {
        return (Command<T>) super.clone();
    }

}
