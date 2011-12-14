package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.JavascriptPackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.resources.CompressedResourceReference;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.UserSession;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.MenuItem;
import org.maxur.taskun.view.pages.calendar.CalendarPanel;
import org.maxur.taskun.view.pages.user.CurrentUserPanel;

import java.util.List;

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
    private final CommandRepository commands;

    /**
     * The constructor of the Base Page Controller.
     */
    public BasePage() {

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

        this.commands = new CommandRepositoryImpl();

        final List<MenuItem> items = getUserSession().getMenuItems();
        for (MenuItem item : items) {
            if (item.getTargetPage() == this.getClass()) {
                item.setActive(true);
                break;
            }
        }

        add(new Label("title.application" ,new ResourceModel("title.application")));
        add(new MenuPanel("menu"));
        add(new HeaderPanel("header"));
        add(new FooterPanel("footer"));
        add(new CurrentUserPanel("current_user_panel", getCommands()));
        add(new CalendarPanel("calendar_panel", getCommands()));
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
