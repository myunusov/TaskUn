package org.maxur.taskun.view.components;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.view.model.Bean;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public interface BeanWindow<E extends Bean> {

    void show(AjaxRequestTarget target, E model);

}
