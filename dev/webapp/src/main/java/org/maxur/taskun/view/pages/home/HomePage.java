package org.maxur.taskun.view.pages.home;

import org.maxur.taskun.view.pages.BasePage;

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
     * Constructs EmployeePage instance.
     */
    public HomePage() {
        super();
        add(new CurrentPanel("current_panel"));
        add(new TotalPanel("total_panel", getCommands()));
    }

}
