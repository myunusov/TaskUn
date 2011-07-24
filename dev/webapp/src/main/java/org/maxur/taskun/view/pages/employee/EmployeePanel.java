package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.view.components.CommandButton;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.EmployeeBean;

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
        add(new FeedbackPanel("employee_feedback"));

        final CompoundPropertyModel<EmployeeBean> model = new CompoundPropertyModel<EmployeeBean>(employee);
        Form<EmployeeBean> form = new Form<EmployeeBean>("employee_form", model);
        form.add(new Label("first_name", new ResourceModel("edit.employee.first.name")));
        form.add(createTextField("firstName", true));
        form.add(new Label("last_name", new ResourceModel("edit.employee.last.name")));
        form.add(createTextField("lastName", true));
        form.add(new Label("middle_name", new ResourceModel("edit.employee.middle.name")));
        form.add(createTextField("middleName", false));
        add(form);
        Form fakeForm = new Form("fake_form");
        add(fakeForm);

        add(new CommandButton<EmployeeBean>(
                "edit_employee_submit", form, onSubmit, model, new ResourceModel("edit.employee.submit")
        ));

        add(new CommandButton<EmployeeBean>(
                "edit_employee_cancel", fakeForm, onClose, model, new ResourceModel("edit.employee.cancel")
        ));

    }

    /**
     * Factory method for TextField instance.
     *
     * @param expression The non-null id of this component.
     * @param isRequired It's true if value of field is required.
     * @return The instance of TextField<String>.
     */
    private TextField<String> createTextField(
            final String expression,
            final boolean isRequired
    ) {
        final TextField<String> field = new TextField<String>(expression);
        field.add(StringValidator.lengthBetween(
                Employee.MIN_EMPLOYEE_NAME_LENGTH,
                Employee.MAX_EMPLOYEE_NAME_LENGTH
        ));
        field.setRequired(isRequired);
        return field;
    }

}
