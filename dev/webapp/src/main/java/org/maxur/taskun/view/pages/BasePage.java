package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.UserSession;

/**
 * The Base Page Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class BasePage extends WebPage {

    /**
     *  The Serial Version UID.
     */
    private static final long serialVersionUID = -7735630739445684538L;

    /**
     * The constructor of the Base Page Controller.
     */
    public BasePage() {
        final HeaderPanel header = new HeaderPanel("header");
        add(new Label("title", new ResourceModel("application.title")));
        add(new MenuPanel("menu"));
        add(header);
        add(new FooterPanel("footer"));
    }

    /**
     * Get The User Session.
     *
     * @return The User Session.
     */
    public UserSession getUserSession() {
        return (UserSession) getSession();
    }

}
