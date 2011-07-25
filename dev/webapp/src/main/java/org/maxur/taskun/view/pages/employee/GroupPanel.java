package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.components.CommandLink;
import org.maxur.taskun.view.components.HighlightLabel;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.model.employee.EmployeeBeanFactory;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.model.ModelList;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class GroupPanel extends Panel {

    private static final long serialVersionUID = -2071713834365240247L;


    public GroupPanel(
            final String id,
            final EmployeesGroup group,
            final CommandRepository commands
    ) {
        super(id, group);
        add(new Label("resume_title", new ResourceModel("info.group.title")));

        add(new Label("total_title", new ResourceModel("info.group.number")));
        final HighlightLabel total = new HighlightLabel("total", new Model<String>() {
            @Override
            public String getObject() {
                return Integer.toString(group.size());
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

        final CommandLink<EmployeeBeanFactory> create = new CommandLink<EmployeeBeanFactory>(
                "employee_create",
                new EmployeeBeanFactory(group),
                commands,
                "employee.edit"
        );

        create.add(new Label("create_title", new ResourceModel("edit.group.create")));
        add(create);

        final AjaxMarkupContainer<ModelList<EmployeeBean>> removeItem =
                new AjaxMarkupContainer<ModelList<EmployeeBean>>("remove_item", group) {
                    @Override
                    public boolean isVisible() {
                        return group.getSelectedCount() > 0;
                    }
                };
        add(removeItem);

        final CommandLink remove = new CommandLink<EmployeesGroup>(
                "employee_remove",
                group,
                commands,
                "employee.remove"
        );

        removeItem.add(remove);
        remove.add(new Label("remove_title", new ResourceModel("edit.group.remove")));

        group.addObservers(total, selected, removeItem);
    }

}
