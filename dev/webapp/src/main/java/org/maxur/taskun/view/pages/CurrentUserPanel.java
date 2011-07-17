package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
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
     * @param user The Current User bean.
     */
    public CurrentUserPanel(final String id, UserBean user) {
        //todo MY All strings may be excluded.
        super(id);
        add(new Label("current_user", "Кто Вы ?"));
    }
}
