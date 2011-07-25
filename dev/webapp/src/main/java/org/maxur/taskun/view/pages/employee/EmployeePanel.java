package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.time.Duration;
import org.apache.wicket.validation.validator.StringValidator;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.components.CommandButton;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.employee.EmployeeBean;

/**
 * Panel for displaying the contents of employee form.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class EmployeePanel extends Panel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -6794175627500512071L;

    /**
     * The Employee panel's constructor.
     *
     * @param id       Employee Form identifier.
     * @param employee The processing Employee.
     * @param onSubmit The parent's command. It's called on submit window.
     * @param onClose  The parent's command. It's called on close window.
     */
    public EmployeePanel(
            final String id,
            final EmployeeBean employee,
            final Command<EmployeeBean> onSubmit,
            final Command<EmployeeBean> onClose
    ) {
        super(id);

        final CompoundPropertyModel<EmployeeBean> model = new CompoundPropertyModel<EmployeeBean>(employee);

        final Form<EmployeeBean> form = new Form<EmployeeBean>("employee_form", model);
        form.setOutputMarkupId(true);

        // attach an ajax validation behavior to all form component's onkeydown
        // event and throttle it down to once per second
        AjaxFormValidatingBehavior.addToAllFormComponents(form, "onkeyup", Duration.ONE_SECOND);

        form.add(new Label("first_name", new ResourceModel("edit.employee.first.name")));
        final TextField<String> firstName = createTextField("firstName", true);
        form.add(firstName);

        form.add(new Label("last_name", new ResourceModel("edit.employee.last.name")));
        final TextField<String> lastName = createTextField("lastName", true);
        form.add(lastName);

        form.add(new Label("middle_name", new ResourceModel("edit.employee.middle.name")));
        final TextField<String> middleName = createTextField("middleName", false);
        form.add(middleName);
        add(form);
        final AjaxButton submit = new CommandButton<EmployeeBean>(
                "edit_employee_submit", form, onSubmit, employee, new ResourceModel("edit.employee.submit")
        ) {
            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
//                target.addComponent(form);
                target.addComponent(firstName);
                target.addComponent(lastName);
                target.addComponent(middleName);
                target.focusComponent(lastName);
                //           lastName.add(new SimpleAttributeModifier("title", "Ошибка"));
                //           lastName.add(new SimpleAttributeModifier("class", "error"));
            }
        };
        add(submit);


        final Form fakeForm = new Form("fake_form");
        add(fakeForm);
        final AjaxButton cancel = new CommandButton<EmployeeBean>(
                "edit_employee_cancel", fakeForm, onClose, employee, new ResourceModel("edit.employee.cancel")
        );
        add(cancel);

    }


    /**
     * Factory method for TextField instance.
     *
     * @param expression The non-null id of this component.
     * @param isRequired It's true if value of field is required.
     * @return The instance of TextField<String>.
     */
    private static TextField<String> createTextField(
            final String expression,
            final boolean isRequired
    ) {
        final TextField<String> field = new TextField<String>(expression);
        field.add(StringValidator.lengthBetween(
                Employee.MIN_EMPLOYEE_NAME_LENGTH,
                Employee.MAX_EMPLOYEE_NAME_LENGTH
        ));
        field.setRequired(isRequired);
        field.setOutputMarkupId(true);
        field.setOutputMarkupPlaceholderTag(true);
        field.add(new ValidationStyleBehavior());

/*        field.add(new AjaxFormComponentUpdatingBehavior("onblur") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                target.addComponent(field);
            }
        });*/

        return field;
    }


    //Check if the component is valid and add the corresponding CSS style attribute
    static class ValidationStyleBehavior extends AbstractBehavior {

        public void onComponentTag(final Component component, final ComponentTag tag) {
            final FormComponent formComponent = (FormComponent) component;
            final boolean valid = formComponent.isValid();
            final boolean empty = null == formComponent.getConvertedInput();
            final FeedbackMessage message = Session.get().getFeedbackMessages().messageForComponent(formComponent);
            final String className;
            if (valid) {
                className = empty ? "empty" : "valid";
            } else {
                className = "invalid";
            }
            tag.getAttributes().put("class", className);
            if (null != message) {
                tag.getAttributes().put("title", message.getMessage());
            }
        }
    }


}
