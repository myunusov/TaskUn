package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.components.EmployeeForm;
import org.maxur.taskun.view.components.EmployeesView;

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
        add(new FeedbackPanel("feedback"));
        add(new Label("current_user", "Кто Вы ?"));
        add(new EmployeeForm("employees_form", HomePage.class));

        add(new Label("employees_title", "Коллеги"));
        add(new EmployeesView("employees", controller.getAllEmployee()));
    }

}
