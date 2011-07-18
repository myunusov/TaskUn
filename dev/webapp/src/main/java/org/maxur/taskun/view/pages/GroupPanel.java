package org.maxur.taskun.view.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.maxur.taskun.view.components.AjaxObserver;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class GroupPanel extends Panel {

    private static final long serialVersionUID = -2071713834365240247L;

    private AjaxObserver groupObserver;


    public GroupPanel(
            final String id,
            final IModel<EmployeesGroup> model,
            final AjaxObserver observer
    ) {
        //todo MY All strings may be excluded.
        super(id, model);
        this.groupObserver = observer;
        final EmployeesGroup group = model.getObject();
        add(new Label("resume_title", "Резюме"));
        final Label total = new Label("total", new Model<String>()  {
            @Override
            public String getObject() {
                return String.format("Всего:\t(%s)", group.getTotal());
            }
        });
        add(total);
        final Label selected = new Label("selected", new Model<String>()  {
            @Override
            public String getObject() {
                return String.format("Выбрано:\t(%s)", group.getSelectedCount());
            }
        });
        add(selected);

        add(new Label("opp_title", "Операции"));
        final RemoveLink remove = new RemoveLink("remove", model);
        add(remove);
        observer.addComponents(total, selected, remove);
    }


    private class RemoveLink extends AjaxFallbackLink<EmployeesGroup> {

        private static final long serialVersionUID = -3408353937500586584L;

        public RemoveLink(final String id, final IModel<EmployeesGroup> model) {
            super(id, model);
            add(new Label("remove_title", "Удалить"));
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            final EmployeesGroup group = getModelObject();
            group.removeSelected();
            groupObserver.update(target);  //TODO  Ajax is not working
            //setResponsePage(HomePage.class);
        }

        @Override
        public boolean isVisible() {
            final EmployeesGroup group = getModelObject();
            return group.getSelectedCount() > 0;
        }
    }
}
