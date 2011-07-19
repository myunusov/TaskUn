package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
* @author Maxim Yunusov
* @version 1.0 7/19/11
*/
public class AjaxMarkupContainer extends WebMarkupContainer implements AjaxObserver {

    private static final long serialVersionUID = -9019267315139286238L;

    public AjaxMarkupContainer(final String id, final IModel<EmployeesGroup> model) {
        super(id, model);
    }

    @Override
    public void update(AjaxRequestTarget target) {
        target.addComponent(this);
    }

    @Override
    public Component get() {
        return this;
    }
}
