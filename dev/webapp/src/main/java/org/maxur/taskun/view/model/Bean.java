package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.Model;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/21/11
 */
public abstract class Bean<T extends Serializable> extends Model<T> {

    private static final long serialVersionUID = 6173408621367263391L;

    private final AjaxChangeManager changeManager;

    protected Bean() {
        super();
        InjectorHolder.getInjector().inject(this);
        changeManager = new AjaxChangeManager();
    }

    protected Bean(final T object) {
        super(object);
        InjectorHolder.getInjector().inject(this);
        changeManager = new AjaxChangeManager();
    }

    public void addObserver(final AjaxObserver observer) {
        changeManager.addComponent(observer);
    }

    public void addObservers(final AjaxObserver... observers) {
        changeManager.addComponents(observers);
    }

    public void update(final AjaxRequestTarget target) {
        if (target != null) {
            changeManager.update(target);
        }
    }

    @Override
    public void detach() {
    }
}
