package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.border.MarkupComponentBorder;
import org.apache.wicket.markup.html.form.FormComponent;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class RequiredBorder extends MarkupComponentBorder {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8588038995408231102L;

    public void renderAfter(final Component component) {
        if (!(component instanceof FormComponent)) {
            throw new IllegalArgumentException("DefaultFocusBehavior: component must be instanceof FormComponent");
        }
        final FormComponent fc = (FormComponent) component;
        if (fc.isRequired()) {
            super.renderAfter(component);
        }
    }
}
