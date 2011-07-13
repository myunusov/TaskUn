package org.maxur.taskun.view;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.model.UserSession;
import org.maxur.taskun.view.pages.ExamplePage;
import org.maxur.taskun.view.pages.HomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This class represents Taskun Application and it's context.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
@Component(value = "wicketApplication")
public class TaskunApplication extends WebApplication {

    /**
     * The ApplicationController bean.
     */
    private transient ApplicationController applicationController;

    /**
     * @see org.apache.wicket.Application#getHomePage()
     * @return Home page class for this application
     */
    @Override
    public Class<? extends Page> getHomePage() {
// TODO MY  Create short url as  mountBookmarkablePage("/example", ExamplePage.class);
        return HomePage.class;
    }


    /**
     * Setter on Application Controller bean.
     * @param controller The Application Controller bean.
     */
    @Autowired
    public void setApplicationController(final ApplicationController controller) {
        this.applicationController = controller;
    }

    /**
     * @see org.apache.wicket.protocol.http.WebApplication#newSession(org.apache.wicket.Request,
     *                                          org.apache.wicket.Response)
     * @param request Represents http request.
     * @param response Represents http response.
     * @return new UserSession for represented request.
     */
    @Override
    public Session newSession(final Request request, final Response response) {
        return new UserSession(request, applicationController, createMenuItems());
    }

    /**
     * Create Menu Items.
     * @return List of Menu Items as MenuItems class.
     */
        //TODO MY must be exclude string constants
    private MenuItems createMenuItems() {
        final MenuItems result = new MenuItems(3);
        result.add("Главная", HomePage.class, true);
        result.add("Блог", ExamplePage.class);
        result.add("О программе", ExamplePage.class);
        return result;
    }

}
