package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.StringValidator;
import org.maxur.taskun.datasource.hibernate.EmployeeImpl;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.view.state.UserSession;

/**
* @author Maxim Yunusov
* @version 1.0 7/10/11
*/
public class EmployeeForm extends Form<Employee> {

    private static final long serialVersionUID = 816388810515322983L;

    private final UserSession session;
    private Class<? extends WebPage> page;

    public EmployeeForm(final String id, final UserSession session, final Class<? extends WebPage> page) {
        super(id, new CompoundPropertyModel<Employee>(new EmployeeImpl()));
        this.session = session;
        this.page = page;
        add(new Label("first_name", "Имя"));
        add(createTextField("firstName", true));
        add(new Label("last_name", "Фамилия"));
        add(createTextField("lastName", true));
        add(new Label("middle_name", "Отчество"));
        add(createTextField("middleName", false));
    }

    private TextField<String> createTextField(final String expression, final boolean isRequired) {
        TextField<String> field = new TextField<String>(expression);
        field.add(StringValidator.lengthBetween(1, 50));
        field.setRequired(isRequired);
        return field;
    }

    @Override
    protected void onSubmit() {
        session.saveEmployee(getModelObject());
        setResponsePage(page);
    }
}
