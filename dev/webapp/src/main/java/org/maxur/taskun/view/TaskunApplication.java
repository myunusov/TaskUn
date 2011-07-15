package org.maxur.taskun.view;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.maxur.taskun.view.model.MenuItems;
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

    /**
     *  It is default encoding for markup files.
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * @see org.springframework.context.ApplicationContext
     *
     */
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Home page class for this application.
     */
    private Class<? extends WebPage> homePage;

    /**
     * Main menu items.
     */
    private MenuItems menuItems;


    /**
     * Static getter for get the TaskunApplication singleton instance.
     * @return the TaskunApplication singleton instance.
     */
    public static TaskunApplication get() {
        return (TaskunApplication) WebApplication.get();
    }

    /**
     * @see org.apache.wicket.protocol.http.WebApplication#init()
     */
    @Override
    protected final void init() {
        super.init();
        springInjection();
    }

    /**
     * Init listener for spring inject. This method exclude by Unit Test needs.
     */
    protected void springInjection() {
            addComponentInstantiationListener(
                    new SpringComponentInjector(this, applicationContext, true)
            );
    }

    /**
     * Internal initialization. This method is not part of Wicket API.
     * It's workaround for directly call init in spring context.
     */
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
     * Getter for get Home Page class.
     *
     * @return Home page class for this application
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public final Class<? extends Page> getHomePage() {
        return homePage;
    }

    /**
     * Setter for set the Home Page.
     *
     * @param page The Home Page.
     */
    @Autowired
    public final void setHomePage(final Class<? extends WebPage> page) {
        mountBookmarkablePage("/home", page);
        this.homePage = page;
    }

    /**
     * Setter for set Main Menu items.
     *
     * @param items Main Menu items.
     */
    @Autowired
    public final void setMenuItems(final MenuItems items) {
        this.menuItems =  items;
    }

    /**
     * Setter for set  applicationContext without Spring inject.
     * @param applicationContext The ApplicationContext.
     */
    public void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * @see org.apache.wicket.Application#newSession(org.apache.wicket.Request,
     *      org.apache.wicket.Response)
     *
     * @param request  Represents http request.
     * @param response Represents http response.
     * @return new UserSession for represented request.
     * @see org.apache.wicket.protocol.http.WebApplication#newSession(org.apache.wicket.Request,
     *      org.apache.wicket.Response)
     */
    @Override
    public Session newSession(final Request request, final Response response) {
        return new UserSession(request, menuItems);
    }

}
