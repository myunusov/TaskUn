package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.behavior.DefaultFocusBehavior;
import org.maxur.taskun.view.behavior.DynamicUpdateBehavior;
import org.maxur.taskun.view.behavior.ErrorHighlightBehavior;
import org.maxur.taskun.view.behavior.Jsr303FormValidator;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class TaskunForm<T> extends Form<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 570532221624657729L;
    private final Class<T> clazz;

    public TaskunForm(final String id, final IModel<T> iModel, final Class<T> clazz) {
        super(id, iModel);
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
