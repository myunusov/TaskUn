package org.maxur.taskun.view.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public interface BeanWindow<T> {

    void show(AjaxRequestTarget target, IModel<T> model);

}
