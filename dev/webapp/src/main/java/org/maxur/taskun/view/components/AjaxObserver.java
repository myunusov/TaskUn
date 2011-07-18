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
public class AjaxObserver implements Serializable {

    private static final long serialVersionUID = 7353171996030881918L;

    private Collection<Component> components = new ArrayList<Component>();

    public void update(final AjaxRequestTarget target) {
        if (target != null) {
            for (Component component : components) {
                target.addComponent(component);
            }
        }
    }

    public void addComponent(final Component component) {
        component.setOutputMarkupId(true);
        component.setOutputMarkupPlaceholderTag(true);
        this.components.add(component);
    }

    public void addComponents(final Component... values) {
        for (Component component : values) {
            addComponent(component);
        }
    }
}
