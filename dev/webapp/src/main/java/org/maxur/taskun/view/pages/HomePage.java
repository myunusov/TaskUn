package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.components.EmployeeForm;
import org.maxur.taskun.view.components.EmployeesView;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
 * The Home Page Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class HomePage extends BasePage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = 1773451707947074585L;

    /**
     * The ApplicationController bean. It's injected by Wicket IoC.
     */
    @SpringBean
    private ApplicationController controller;


    /**
     *  Constructs HomePage instance.
     *
     */
    public HomePage() {
        //todo MY All strings may be excluded.

        final EmployeesGroup group = new EmployeesGroup(controller.getAllEmployee());

        add(new FeedbackPanel("feedback"));
        add(new Label("current_user", "Кто Вы ?"));
        add(new EmployeeForm("employees_form", HomePage.class));

        add(new Label("resume_title", "Резуме"));
        add(new Label("total", String.format("Всего:\t(%s)", group.getTotal())));
        add(new Label("selected", String.format("Выбрано:\t(%s)", group.getSelectedCount())));

        add(new Label("employees_title", "Коллеги"));
        add(new EmployeesView("employees", group));
    }

}
