package org.maxur.taskun.view.behavior;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class DynamicUpdateBehavior extends AjaxFormComponentUpdatingBehavior {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2155331666051730683L;

    private final FormComponent formComponent;

    public DynamicUpdateBehavior(final FormComponent component) {
        super("onblur");
        component.setOutputMarkupId(true);
        component.setOutputMarkupPlaceholderTag(true);
        this.formComponent = component;
    }

    @Override
    protected void onUpdate(final AjaxRequestTarget target) {
        target.addComponent(formComponent);
    }

    @Override
    protected void onError(final AjaxRequestTarget target, final RuntimeException e) {
        target.addComponent(formComponent);
    }

    public static void addToAllComponent(final Form form) {
        form.visitChildren(FormComponent.class, new ComponentVisitor() {

            @Override
            protected boolean isAccepted(FormComponent fc) {
                return !(fc instanceof Button);
            }

            @Override
            protected Object doAdd(FormComponent fc) {
                fc.add(new DynamicUpdateBehavior(fc));
                return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
            }
        });
    }

}
