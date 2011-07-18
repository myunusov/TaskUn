package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
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
        super(id);
        add(new Label("current_user", new ResourceModel("current.user.title")));
        add(new Label("unknown", new ResourceModel("current.user.unknown")));
        final Form form = new LogOffForm("current_user_form");
        add(form);
        final Button button = new LogOffButton("logoff");
        form.add(button);
    }

    private static class LogOffButton extends Button {

        private static final long serialVersionUID = 1625717014933218924L;

        public LogOffButton(final String id) {
            super(id, new ResourceModel("current.user.logoff"));
        }

        @Override
        public void onSubmit() {
            //TODO stub
            info("Log Off");
        }
    }

    private static class LogOffForm extends Form {

        private static final long serialVersionUID = -1522617182842026034L;

        public LogOffForm(final String id) {
            super(id);
        }
    }

}
