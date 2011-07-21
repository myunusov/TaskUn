package org.maxur.taskun.view.pages;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.components.AjaxObserver;
import org.maxur.taskun.view.components.HighlightLabel;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class GroupPanel extends Panel {

    private static final long serialVersionUID = -2071713834365240247L;

    private final EmployeeWindow createDialog;

    public GroupPanel(
            final String id,
            final IModel<EmployeesGroup> model,
            EmployeeWindow employeeWindow
    ) {
        super(id, model);
        this.createDialog = employeeWindow;
        final EmployeesGroup group = model.getObject();
        add(new Label("resume_title", new ResourceModel("info.group.title")));

        add(new Label("total_title", new ResourceModel("info.group.number")));
        final HighlightLabel total = new HighlightLabel("total", new Model<String>() {
            @Override
            public String getObject() {
                return group.getTotal().toString();
            }
        });
        add(total);

        add(new Label("selected_title", new ResourceModel("info.group.select")));
        final HighlightLabel selected = new HighlightLabel("selected", new Model<String>() {
            @Override
            public String getObject() {
                return group.getSelectedCount().toString();
            }
        });
        add(selected);

        add(new Label("opp_title", new ResourceModel("edit.group.title")));
        final CreateLink create = new CreateLink("create", model);
        add(create);

        final AjaxMarkupContainer removeItem = new AjaxMarkupContainer("remove_item", model) {
            @Override
            public boolean isVisible() {
                return group.getSelectedCount() > 0;
            }
        };
        add(removeItem);

        final RemoveLink remove = new RemoveLink("remove", model);
        removeItem.add(remove);

        group.addObservers(total, selected, removeItem);
    }


    private class RemoveLink extends AjaxFallbackLink<EmployeesGroup> implements AjaxObserver {

        private static final long serialVersionUID = -3408353937500586584L;

        public RemoveLink(final String id, final IModel<EmployeesGroup> model) {
            super(id, model);
            add(new Label("remove_title", new ResourceModel("edit.group.remove")));
        }

        @Override
        public void onClick(final AjaxRequestTarget target) {
            final EmployeesGroup group = getModelObject();
            group.removeSelected(target);
        }

        @Override
        public boolean isVisible() {
            final EmployeesGroup group = getModelObject();
            return group.getSelectedCount() > 0;
        }

        @Override
        public void update(AjaxRequestTarget target) {
            target.addComponent(this);
        }

        @Override
        public Component get() {
            return this;
        }
    }

    private class CreateLink extends AjaxFallbackLink<EmployeesGroup> {

        private static final long serialVersionUID = 4034219560267947425L;

        public CreateLink(final String id, final IModel<EmployeesGroup> model) {
            super(id, model);
            add(new Label("create_title", new ResourceModel("edit.group.create")));
        }

        @Override
        public void onClick(AjaxRequestTarget target) {
            createDialog.show(target);
        }
    }


}
