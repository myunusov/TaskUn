package org.maxur.taskun.view;

import images.ImagesScope;
import org.apache.wicket.Page;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.pages.home.HomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * This class represents Taskun Application and it's context.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
@Component(value = "wicketApplication")
public class TaskunApplication extends AuthenticatedWebApplication {

    /**
     * It is default encoding for markup files.
     */
    private static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Home page class for this application.
     */
    private Class<? extends WebPage> homePage;

    /**
     * Main menu items.
     */
    private MenuItems menuItems;

    private boolean isInitialized = false;


    /**
     * Static getter for get the TaskunApplication singleton instance.
     *
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
        if (isInitialized) {
            super.init();
            springInjection();
        }
    }

    /**
     * Init listener for spring inject. This method exclude from init() by Unit Test needs.
     */
    protected void springInjection() {
        addComponentInstantiationListener(
                new SpringComponentInjector(this)
        );
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return HomePage.class;
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return UserSession.class;
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

        mountResources(ImagesScope.class, "images");
        isInitialized = true;
    }

    private void mountResources(final Class clazz, final String directory) {
        java.net.URL url = clazz.getResource(clazz.getSimpleName() + ".class");
        File[] files = new File(url.getPath()).getParentFile().listFiles();

        for (File file : files) {
            String fileName = file.getName();
            if (!fileName.endsWith("class")) {
                mountSharedResource("/" + directory + "/" + fileName,
                        new ResourceReference(clazz, fileName).getSharedResourceKey());
            }
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
    @Lazy
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
    @Lazy
    public final void setMenuItems(final MenuItems items) {
        this.menuItems = items;
    }


}
