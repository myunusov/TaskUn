package org.maxur.taskun.view.components;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.ResourceModel;

/**
 * It solution to manage the localization thing and keeping the power to choose what information to use
 * as the option key
 * http://developme.wordpress.com/2010/03/10/super-easy-dropdownchoice-renderer-for-string-values/
 *
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public class StringBasedChoiceRenderer<T> implements IChoiceRenderer<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5055803135694503993L;

    /**
     * context can be your wicketId or any other string you desire.
     */
    private final String context;

    /**
     * The component owner.
     */
    private Component component;

    /**
     * Constructs Instance of class.
     *
     * @param context   can be your wicketId or any other string you desire.
     *                  this value is used as a prefix in order to get the localized option value.
     * @param component The component owner
     */
    public StringBasedChoiceRenderer(final String context, Component component) {
        this.context = context;
        this.component = component;
    }


    /**
     * To obtain the display value we localize the string obtained concatenating
     * the constructor context value and the actual value of the option.
     *
     * @param object the actual object
     * @return the value meant for displaying to an end user
     */
    @Override
    public Object getDisplayValue(final T object) {
        final ResourceModel model = new ResourceModel(context + "." + object, object.toString());
        return model.wrapOnAssignment(component).getObject();
    }

    /**
     * This basic implementation use the entire string value as the option key.
     *
     * @param object The object for which the id should be generated
     * @param index  The index of the object in the choices list.
     * @return String
     */
    @Override
    public String getIdValue(final T object, int index) {
        return object.toString();
    }
}
