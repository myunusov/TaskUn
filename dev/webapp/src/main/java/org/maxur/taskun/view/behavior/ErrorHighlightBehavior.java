package org.maxur.taskun.view.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class ErrorHighlightBehavior extends AbstractBehavior {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -7625740145413340458L;

    public void onComponentTag(Component component, ComponentTag tag) {
        if (!(component instanceof FormComponent)) {
            throw new IllegalArgumentException("DefaultFocusBehavior: component must be instanceof FormComponent");
        }
        final FormComponent fc = (FormComponent) component;
        final boolean valid = fc.isValid();
        final boolean empty = null == fc.getConvertedInput();
        final FeedbackMessage message = Session.get().getFeedbackMessages().messageForComponent(fc);
        final String className = valid ? empty ? "empty" : "valid" : "invalid";
        tag.getAttributes().put("class", className);
        if (null != message) {
            tag.getAttributes().put("title", message.getMessage());
        }
    }

    public static void addToAllComponent(final Form form) {
        form.visitChildren(FormComponent.class, new ComponentVisitor() {

            @Override
            protected boolean isAccepted(final FormComponent fc) {
                return !(fc instanceof Button);
            }

            @Override
            protected Object doAdd(final FormComponent fc) {
                fc.add(new ErrorHighlightBehavior());
                return CONTINUE_TRAVERSAL_BUT_DONT_GO_DEEPER;
            }
        });
    }

}

