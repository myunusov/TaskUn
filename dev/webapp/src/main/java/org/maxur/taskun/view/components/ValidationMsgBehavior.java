package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class ValidationMsgBehavior extends AbstractBehavior {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 417117641460055655L;

    public void onRendered(Component component) {
        if (!(component instanceof FormComponent)) {
            throw new IllegalArgumentException("DefaultFocusBehavior: component must be instanceof FormComponent");
        }
        final FormComponent fc = (FormComponent) component;
        if (!fc.isValid()) {
            String error;
            if (fc.hasFeedbackMessage()) {
                error = fc.getFeedbackMessage().getMessage().toString();
            } else {
                error = "Your input is invalid.";
            }
            fc.getResponse().write("<div class=\"validationMsg\">" + error + "</div>");
        }
    }
}