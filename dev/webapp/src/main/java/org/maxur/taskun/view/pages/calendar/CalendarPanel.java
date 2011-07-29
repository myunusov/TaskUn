package org.maxur.taskun.view.pages.calendar;

import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.model.CommandRepository;

/**
 * Panel for displaying the current user.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class CalendarPanel extends Panel {

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
    public CalendarPanel(
            final String id,
            final CommandRepository commands
    ) {
        super(id);
    }

}
