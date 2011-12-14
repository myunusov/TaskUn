package org.maxur.taskun.view.pages.admin;

import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.model.CommandRepository;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/29/11
 */
public final class AdminPanel extends Panel {

    private static final long serialVersionUID = 4267391248311786583L;

    public AdminPanel(String id, CommandRepository commands) {
        super(id);
    }
}
