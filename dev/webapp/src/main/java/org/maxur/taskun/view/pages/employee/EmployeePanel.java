package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.feedback.ComponentFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.time.Duration;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.components.CommandButton;
import org.maxur.taskun.view.components.TaskunForm;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.MapRenderer;
import org.maxur.taskun.view.model.employee.EmployeeBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
     * @param bean     The processing Employee model.
     * @param onSubmit The parent's command. It's called on submit window.
     * @param onClose  The parent's command. It's called on close window.
     */
    public EmployeePanel(
            final String id,
            final EmployeeBean bean,
            final Command<EmployeeBean> onSubmit,
            final Command<EmployeeBean> onClose
    ) {
        super(id);

        final CompoundPropertyModel<Employee> model = new CompoundPropertyModel<Employee>(bean.getObject());
        final Image image = new Image("employee_img");
        image.add(new SimpleAttributeModifier("src", "/images/" + bean.getImageName()));
        add(image);

        final Form<Employee> form = new TaskunForm<Employee>("employee_form", model, Employee.class);
        form.setOutputMarkupId(true);
        add(form);

        final FeedbackPanel feedback = new FeedbackPanel("feedback", new ComponentFeedbackMessageFilter(form));
        add(feedback.setOutputMarkupId(true));

        // attach an ajax validation behavior to all form component's onkeydown
        // event and throttle it down to once per second
        AjaxFormValidatingBehavior.addToAllFormComponents(form, "onkeyup", Duration.ONE_SECOND);

        form.add(new Label("last_name", new ResourceModel("edit.employee.last.name")));

        final TextField<String> lastName = new TextField<String>("lastName");
        form.add(lastName);

        form.add(new Label("first_name", new ResourceModel("edit.employee.first.name")));

        final TextField<String> firstName = new TextField<String>("firstName");
        form.add(firstName);

        form.add(new Label("middle_name", new ResourceModel("edit.employee.middle.name")));

        final TextField<String> middleName = new TextField<String>("middleName");
        form.add(middleName);

        form.add(new Label("gender_label", new ResourceModel("edit.employee.gender")));


        Map<Gender, String> map = new HashMap<Gender, String>();
        map.put(Gender.UNKNOWN, "Неизвестен");
        map.put(Gender.MALE,    "Мужской");
        map.put(Gender.FEMALE,  "Женский");
        IChoiceRenderer<Gender> renderer = new MapRenderer<Gender>(map);
        final DropDownChoice<Gender> gender =
                new DropDownChoice<Gender>("gender", Arrays.asList(Gender.values()), renderer);
        form.add(gender);

        final AjaxButton submit = new CommandButton<EmployeeBean>(
                "edit_employee_submit", form, onSubmit, bean, new ResourceModel("edit.employee.submit")
        ) {
            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> form) {
                target.addComponent(firstName);
                target.addComponent(lastName);
                target.addComponent(middleName);
                target.addComponent(gender);
                target.addComponent(feedback);
            }
        };
        add(submit);

        final Form fakeForm = new Form("fake_form");
        add(fakeForm);
        final AjaxButton cancel = new CommandButton<EmployeeBean>(
                "edit_employee_cancel", fakeForm, onClose, bean, new ResourceModel("edit.employee.cancel")
        );
        cancel.setDefaultFormProcessing(false);
        add(cancel);
    }

}
