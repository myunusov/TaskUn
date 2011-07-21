package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.view.components.AjaxChangeManager;
import org.maxur.taskun.view.components.AjaxObserver;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/21/11
 */
public abstract class Bean implements Serializable {

    private static final long serialVersionUID = 6173408621367263391L;

    protected final AjaxChangeManager changeManager;

    protected Bean() {
        changeManager = new AjaxChangeManager();
    }

    public void addObserver(final AjaxObserver observer) {
        changeManager.addComponent(observer);
    }

    public void addObservers(final AjaxObserver... observers) {
        changeManager.addComponents(observers);
    }

    void update(final AjaxRequestTarget target) {
        if (target != null) {
            changeManager.update(target);
        }
    }

}
