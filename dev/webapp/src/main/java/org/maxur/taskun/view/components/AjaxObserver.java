package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/19/11
 */
public interface AjaxObserver {

    void update(final AjaxRequestTarget target);

    Component get();
}
