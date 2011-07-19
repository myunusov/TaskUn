package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class AjaxChangeManager implements Serializable {

    private static final long serialVersionUID = 7353171996030881918L;

    private Collection<AjaxObserver> observers = new ArrayList<AjaxObserver>();

    public void update(final AjaxRequestTarget target) {
        if (target != null) {
            for (AjaxObserver observer : observers) {
                observer.update(target);
            }
        }
    }

    public void addComponent(final AjaxObserver observer) {
        Component component = observer.get();
        component.setOutputMarkupId(true);
        component.setOutputMarkupPlaceholderTag(true);
        this.observers.add(observer);
    }

    public void addComponents(final AjaxObserver... values) {
        for (AjaxObserver observer : values) {
            addComponent(observer);
        }
    }
}
