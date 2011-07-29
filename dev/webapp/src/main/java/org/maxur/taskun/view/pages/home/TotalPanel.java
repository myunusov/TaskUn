package org.maxur.taskun.view.pages.home;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.model.CommandRepository;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class TotalPanel extends Panel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2071713834365240247L;

    public TotalPanel(
            final String id,
            final CommandRepository commands
    ) {
        super(id);
        add(new Label("today", "14.08.2007")); // TODO stub
        add(new Label("planed",     "2"));
        add(new Label("completed",  "0"));
    }

}
