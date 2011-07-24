package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.model.AjaxObserver;

/**
* @author Maxim Yunusov
* @version 1.0 7/19/11
*/
public class HighlightLabel extends Label implements AjaxObserver {

     /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 4750097658321689822L;

    private String value = "";

    public HighlightLabel(final String id, IModel<String> model) {
        super(id, model);
        value = model.getObject();
    }

    @Override
    public void update(final AjaxRequestTarget target) {
        final String newValue = (String) getDefaultModel().getObject();
        if (!value.equals(newValue)) {
            value = newValue;
            target.addComponent(this);
            target.appendJavascript(String.format("new Effect.Highlight($('%s'));",getMarkupId()));
        }
    }

    @Override
    public Component get() {
        return this;
    }
}
