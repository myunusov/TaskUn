package org.maxur.taskun.view;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.model.UserSession;
import org.maxur.taskun.view.pages.ExamplePage;
import org.maxur.taskun.view.pages.HomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * This class represents Taskun Application and it's context.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
@Component(value = "wicketApplication")
public class TaskunApplication extends WebApplication {

    private static final String DEFAULT_ENCODING = "UTF-8";

    @Autowired
    private ApplicationContext applicationContext;

    private Class<? extends WebPage> homePage;


    public static TaskunApplication get() {
        return (TaskunApplication) WebApplication.get();
    }

    /**
     * @see org.apache.wicket.protocol.http.WebApplication#init()
     */
    @Override
    protected void init() {
        super.init();
        springInjection();
    }

    protected void springInjection() {
            addComponentInstantiationListener(
                    new SpringComponentInjector(this, applicationContext, true)
            );
    }

    @Override
    protected void internalInit() {
        super.internalInit();
        getMarkupSettings().setDefaultMarkupEncoding(DEFAULT_ENCODING);
        getRequestCycleSettings().setResponseRequestEncoding(DEFAULT_ENCODING);

        if (getConfigurationType().equals(WebApplication.DEPLOYMENT)) {
            getMarkupSettings().setStripWicketTags(true);
            getMarkupSettings().setStripComments(true);
            getMarkupSettings().setCompressWhitespace(true);
        }
    }

    /**
     * @return Configuration Type
     * @see org.apache.wicket.protocol.http.WebApplication#getConfigurationType()
     */
    @Override
    public String getConfigurationType() {
        return super.getConfigurationType();
        //return WebApplication.DEVELOPMENT;
    }

    /**
     * @return Home page class for this application
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends Page> getHomePage() {
        return homePage;
    }

    /**
     * Setter which set the Home Page.
     * @param homePage The Home Page.
     */
    @Autowired
    public void setHomePage(final Class<? extends WebPage> homePage) {
        mountBookmarkablePage("/home", homePage);
        this.homePage = homePage;
    }

    /**
     * @param request  Represents http request.
     * @param response Represents http response.
     * @return new UserSession for represented request.
     * @see org.apache.wicket.protocol.http.WebApplication#newSession(org.apache.wicket.Request,
     *      org.apache.wicket.Response)
     */
    @Override
    public Session newSession(final Request request, final Response response) {
        return new UserSession(request, createMenuItems());
    }

    /**
     * Create Menu Items.
     *
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
