package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.UserSession;
import org.maxur.taskun.view.model.CommandRepository;

/**
 * The Base Page Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class BasePage extends WebPage {

    /**
     * The Serial Version UID.
     */
    private static final long serialVersionUID = -7735630739445684538L;

    /**
     * The commands repository
     */
    private CommandRepository commands;

    /**
     * The constructor of the Base Page Controller.
     */
    public BasePage() {
        final HeaderPanel header = new HeaderPanel("header");

        final CompressedResourceReference style =
                new CompressedResourceReference(getClass(), "/css/style.css");
        add(CSSPackageResource.getHeaderContribution(style));

        final CompressedResourceReference prototype =
                new CompressedResourceReference(getClass(), "/js/prototype.js");
        add(JavascriptPackageResource.getHeaderContribution(prototype));

        final CompressedResourceReference scriptaculous =
                new CompressedResourceReference(getClass(), "/js/scriptaculous.js");
        add(JavascriptPackageResource.getHeaderContribution(scriptaculous));

        final CompressedResourceReference effects =
                new CompressedResourceReference(getClass(), "/js/effects.js");
        add(JavascriptPackageResource.getHeaderContribution(effects));

        add(new Label("title.application" ,new ResourceModel("title.application")));
        add(new MenuPanel("menu"));
        add(header);
        add(new FooterPanel("footer"));
        commands = new CommandRepository();
    }

    /**
     * Get The User Session.
     *
     * @return The User Session.
     */
    public UserSession getUserSession() {
        return (UserSession) getSession();
    }

    /**
     * Fet the Commands repository.
     *
     * @return the Commands repository.
     */
    public CommandRepository getCommands() {
        return commands;
    }
}
