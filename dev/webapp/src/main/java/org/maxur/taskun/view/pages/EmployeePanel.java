package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.maxur.taskun.domain.Employee;
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
     * @param page     The Response Page.
     */
    public EmployeePanel(
            final String id,
            final EmployeeBean employee,
            final Class<? extends WebPage> page
    ) {
        super(id);
        add(new FeedbackPanel("feedback"));
        add(new EmployeeForm("employee_form", employee, page));
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
         * The Response Page.
         */
        private final Class<? extends WebPage> responsePage;

        /**
         * Constructs EmployeeForm instance.
         *
         * @param id       Employee Form identifier.
         * @param employee The processing Employee.
         * @param page     The Response Page.
         */
        public EmployeeForm(
                final String id,
                final EmployeeBean employee,
                final Class<? extends WebPage> page
        ) {
            super(id, new CompoundPropertyModel<EmployeeBean>(employee));
            this.responsePage = page;
            add(new Label("first_name", new ResourceModel("edit.employee.first.name")));
            add(createTextField("firstName", true));
            add(new Label("last_name", new ResourceModel("edit.employee.last.name")));
            add(createTextField("lastName", true));
            add(new Label("middle_name", new ResourceModel("edit.employee.middle.name")));
            add(createTextField("middleName", false));
            add(new Button("submit", new ResourceModel("edit.employee.submit")));
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

        /**
         * This method is called on Form Submit.
         */
        @Override
        protected final void onSubmit() {
            //todo MY it must be ajax
            try {
                getModelObject().save();
            } catch (Exception e) {
                info((new ResourceModel("error.system")).getObject());
                //todo MY it must be processed
            }
            setResponsePage(responsePage);
        }
    }
}
