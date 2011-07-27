package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.AbstractTextComponent;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class DefaultFocusBehavior extends AbstractBehavior {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -4891399118136854774L;

    private Component component;

    private boolean isEnable;

    @Override
    public void bind(Component component) {
        if (!(component instanceof FormComponent)) {
            throw new IllegalArgumentException("DefaultFocusBehavior: component must be instanceof FormComponent");
        }
        this.component = component;
        component.setOutputMarkupId(true);
        isEnable = true;
    }

    @Override
    public void renderHead(IHeaderResponse iHeaderResponse) {
        super.renderHead(iHeaderResponse);
        if (isEnable) {
            iHeaderResponse.renderOnLoadJavascript("document.getElementById('"
                    + component.getMarkupId() + "').focus();");
            isEnable = false;
        }
    }

    public static void addToAll(final Form form) {
        form.visitChildren(FormComponent.class, new DefaultFocusComponentIVisitor() {

            public boolean found = false;

            @Override
            protected Object doAdd(FormComponent fc) {
                final DefaultFocusBehavior behavior = new DefaultFocusBehavior();
                fc.add(behavior);
                behavior.isEnable = !found;
                found = true;
                return Component.IVisitor.CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
            }
        });
    }

    public static void setToFirst(final Form form) {
        form.visitChildren(FormComponent.class, new DefaultFocusComponentIVisitor() {
        });
    }

    public static void setToFirstError(final Form form) {
        form.visitChildren(FormComponent.class, new DefaultFocusComponentIVisitor() {

            protected boolean isAccepted(final FormComponent fc) {
                return super.isAccepted(fc) && !fc.isValid();
            }

        });
    }

    private static abstract class DefaultFocusComponentIVisitor extends ComponentVisitor {

        protected boolean isAccepted(final FormComponent fc) {
            return fc.isEnabled() && fc.isVisible() && !(fc instanceof Button) &&
                    (fc instanceof DropDownChoice || fc instanceof AbstractTextComponent);
        }

        @Override
        protected Object doAdd(FormComponent fc) {

            final List<IBehavior> behaviors = fc.getBehaviors();
            for (IBehavior behavior : behaviors) {
                if (behavior instanceof DefaultFocusBehavior) {
                    ((DefaultFocusBehavior) behavior).isEnable = true;
                    return Component.IVisitor.STOP_TRAVERSAL;
                }
            }
            return Component.IVisitor.CONTINUE_TRAVERSAL;
        }
    }
}
