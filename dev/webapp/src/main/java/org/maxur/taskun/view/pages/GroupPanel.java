package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class GroupPanel extends Panel {


    private static final long serialVersionUID = -2071713834365240247L;

    private final EmployeesGroup group;

    public GroupPanel(final String id, final EmployeesGroup group) {
        //todo MY All strings may be excluded.
        super(id);
        this.group = group;
        add(new Label("resume_title", "Резюме"));
        add(new Label("total", String.format("Всего:\t(%s)", group.getTotal())));
        add(new Label("selected", String.format("Выбрано:\t(%s)", group.getSelectedCount())));
        add(new Label("opp_title", "Операции"));
        add(new RemoveLink("remove"));
    }


    private class RemoveLink extends Link {

        private static final long serialVersionUID = -3408353937500586584L;

        public RemoveLink(final String id) {
            super(id);
            add(new Label("remove_title", "Удалить"));
        }

        @Override
        public void onClick() {
            group.removeSelected();
            setResponsePage(HomePage.class);
        }

        @Override
        public boolean isVisible() {
            return !group.isEmpty();
        }
    }
}
