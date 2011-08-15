package org.maxur.taskun.view.pages.user;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.commands.ShowModalWindowCommand;
import org.maxur.taskun.view.components.CommandLink;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.UserBean;

/**
 * Panel for displaying the current user.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class CurrentUserPanel extends Panel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -5584843370240744742L;

    /**
     * The Current User panel's constructor.
     *
     * @param id The Current User Panel identifier.
     * @param commands
     */
    public CurrentUserPanel(
            final String id,
            final CommandRepository commands
    ) {
        super(id);        // TODO Current User is get from session
        final LogonWindow logonWindow = new LogonWindow("logon_window", commands);
        add(logonWindow);

        add(new Label("current_user", new ResourceModel("current.user.title")));
        add(new Label("unknown", new ResourceModel("current.user.unknown")));

        final CommandLink<UserBean> logon = new CommandLink<UserBean>(
                "logon_link",
                new UserBean(),
                new ShowModalWindowCommand<UserBean>(logonWindow)
        );
        add(logon);

        commands.persist("user.submit", new UserLogonCommand());
    }


    private static class UserLogonCommand extends Command<UserBean> {

        private static final long serialVersionUID = -642230153599081916L;

        @Override
        public void execute(final AjaxRequestTarget target, final UserBean model) {
        }
    }
}
