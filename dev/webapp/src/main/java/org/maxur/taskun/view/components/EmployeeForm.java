package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.maxur.taskun.datasource.hibernate.EmployeeImpl;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.services.TaskunServiceException;

/**
* @author Maxim Yunusov
* @version 1.0 7/10/11
*/
public class EmployeeForm extends Form<Employee> {

     /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 816388810515322983L;

    /**
     * The Response Page.
     */
    private Class<? extends WebPage> responsePage;


    @SpringBean
    private ApplicationController controller;


    public void setController(final ApplicationController controller) {
        this.controller = controller;
    }

    /**
     * Constructs EmployeeForm instance.
     * @param id  Employee Form identifier.
     * @param page The Response Page.
     *
     * @todo MY All String constants should be excluded to resources
     */
    public EmployeeForm(final String id, final Class<? extends WebPage> page) {
        super(id, new CompoundPropertyModel<Employee>(new EmployeeImpl()));
        this.responsePage = page;
        add(new Label("first_name", "Имя"));
        add(createTextField("firstName", true));
        add(new Label("last_name", "Фамилия"));
        add(createTextField("lastName", true));
        add(new Label("middle_name", "Отчество"));
        add(createTextField("middleName", false));
    }

    /**
     * Factory method for TextField instance.
     * @param expression The non-null id of this component.
     * @param isRequired  It's true if value of field is required.
     *
     * @return The instance of TextField<String>.
     */
    private TextField<String> createTextField(
            final String expression,
            final boolean isRequired
    ) {
        TextField<String> field = new TextField<String>(expression);
        field.add(StringValidator.lengthBetween(1, Employee.MAX_EMPLOYEE_NAME_LENGTH));
        field.setRequired(isRequired);
        return field;
    }

    /**
     * This method is called on Form Submit.
     */
    @Override
    protected final void onSubmit() {
        try {
            controller.saveEmployee(getModelObject());
        } catch (TaskunServiceException e) {
            //todo MY it must be processed
        }
        setResponsePage(responsePage);
    }
}
