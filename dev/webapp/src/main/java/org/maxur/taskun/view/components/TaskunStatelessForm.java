package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.behavior.DefaultFocusBehavior;
import org.maxur.taskun.view.behavior.DynamicUpdateBehavior;
import org.maxur.taskun.view.behavior.ErrorHighlightBehavior;
import org.maxur.taskun.view.behavior.Jsr303FormValidator;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/17/11
 */
public class TaskunStatelessForm<T> extends StatelessForm<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 570532221624657729L;

    private final Class<T> clazz;

    public TaskunStatelessForm(final String id, final IModel<T> model, final Class<T> clazz) {
        super(id, model);
        this.clazz = clazz;
    }

    public void onBeforeRender() {
        super.onBeforeRender();
        ErrorHighlightBehavior.addToAllComponent(this);
        DynamicUpdateBehavior.addToAllComponent(this);
        Jsr303FormValidator.<T>addToAllComponent(this, this.clazz);
        DefaultFocusBehavior.addToAll(this);
    }

    public void onError() {
        super.onError();
        DefaultFocusBehavior.setToFirstError(this);
    }

}
