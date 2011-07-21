package org.maxur.taskun.view.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.TaskunServiceException;
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
     * @param id          Employee Form identifier.
     * @param employee    The processing Employee.
     * @param modalWindow The Response Page.
     */
    public EmployeePanel(
            final String id,
            final EmployeeBean employee,
            final ModalWindow modalWindow
    ) {
        super(id);
        add(new FeedbackPanel("feedback"));
        EmployeeForm form = new EmployeeForm("employee_form", employee);
        add(form);
        add(new AjaxButton("submit", new ResourceModel("edit.employee.submit"), form) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                try {
                    employee.save(target);
                    modalWindow.close(target);
                } catch (TaskunServiceException e) {
                }
            }
        });
        Form fakeForm = new Form("fake_form");
        add(fakeForm);
        add(new AjaxButton("cancel", new ResourceModel("edit.employee.cancel"), fakeForm) {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                modalWindow.close(target);
            }
        });
    }

    /**
     * The Employee Properties Form.
     */
    private static class EmployeeForm extends Form<EmployeeBean> {

        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = 816388810515322983L;

        /**
         * Constructs EmployeeForm instance.
         *
         * @param id       Employee Form identifier.
         * @param employee The processing Employee.
         */
        public EmployeeForm(
                final String id,
                final EmployeeBean employee
        ) {
            super(id, new CompoundPropertyModel<EmployeeBean>(employee));
            add(new Label("first_name", new ResourceModel("edit.employee.first.name")));
            add(createTextField("firstName", true));
            add(new Label("last_name", new ResourceModel("edit.employee.last.name")));
            add(createTextField("lastName", true));
            add(new Label("middle_name", new ResourceModel("edit.employee.middle.name")));
            add(createTextField("middleName", false));
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
                    Employee.MAX_EMPLOYEE_NAME_LENGTH)
            );
            field.setRequired(isRequired);
            return field;
        }
    }
}
