package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.FormComponent;

import java.util.HashSet;
import java.util.Set;

/**
* @author Maxim Yunusov
* @version 1.0 7/27/11
*/
abstract class ComponentVisitor implements Component.IVisitor<Component> {

    private final Set<FormComponent> visited = new HashSet<FormComponent>();

    public Object component(final Component component) {
        if (component instanceof FormComponent) {
            final FormComponent fc = (FormComponent) component;
            if (!visited.contains(fc)) {
                visited.add(fc);
                if (isAccepted(fc)) {
                    return doAdd(fc);
                }
            }
        }
        return Component.IVisitor.CONTINUE_TRAVERSAL;
    }

    protected abstract boolean isAccepted(FormComponent fc);

    protected abstract Object doAdd(FormComponent fc);
}
