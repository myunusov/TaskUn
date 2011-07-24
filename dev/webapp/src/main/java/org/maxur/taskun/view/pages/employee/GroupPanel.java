package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.components.CommandLink;
import org.maxur.taskun.view.components.HighlightLabel;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.EmployeeBean;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class GroupPanel extends Panel {

    private static final long serialVersionUID = -2071713834365240247L;


    public GroupPanel(
            final String id,
            final IModel<EmployeesGroup> model,
            final CommandRepository commands
    ) {
        super(id, model);
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

        final CommandLink create = new CommandLink<EmployeeBean>(
                "employee_create",
                new Model<EmployeeBean>() {
                    @Override
                    public EmployeeBean getObject() {
                        return model.getObject().createEmployee();
                    }
                },
                commands,
                "employee.edit"
        );

        create.add(new Label("create_title", new ResourceModel("edit.group.create")));
        add(create);

        final AjaxMarkupContainer<EmployeesGroup> removeItem =
                new AjaxMarkupContainer<EmployeesGroup>("remove_item", model) {
                    @Override
                    public boolean isVisible() {
                        return group.getSelectedCount() > 0;
                    }
                };
        add(removeItem);

        final CommandLink remove = new CommandLink<EmployeesGroup>(
                "employee_remove",
                model,
                commands,
                "employee.remove"
        );

        removeItem.add(remove);
        remove.add(new Label("remove_title", new ResourceModel("edit.group.remove")));

        group.addObservers(total, selected, removeItem);
    }

}
