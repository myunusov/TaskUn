package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public abstract class Command<E extends Bean> implements Serializable, Cloneable {

    private static final long serialVersionUID = 6116373389784514896L;

    public abstract void execute(AjaxRequestTarget target, E model);

    @SuppressWarnings("unchecked")
	@Override
    public Command<E> clone() throws CloneNotSupportedException {
        return (Command<E>) super.clone();
    }

}
