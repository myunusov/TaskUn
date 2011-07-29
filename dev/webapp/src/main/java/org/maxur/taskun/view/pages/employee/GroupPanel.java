package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.components.CommandLink;
import org.maxur.taskun.view.components.HighlightLabel;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.ModelList;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.model.employee.EmployeeBeanFactory;
import org.maxur.taskun.view.model.employee.EmployeesGroup;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class GroupPanel extends Panel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2071713834365240247L;

    public GroupPanel(
            final String id,
            final EmployeesGroup group,
            final CommandRepository commands
    ) {
        super(id, group);

        final HighlightLabel total = new HighlightLabel("total", new Model<String>() {
            @Override
            public String getObject() {
                return Integer.toString(group.size());
            }
        });
        add(total);

        final HighlightLabel selected = new HighlightLabel("selected", new Model<String>() {
            @Override
            public String getObject() {
                return group.getSelectedCount().toString();
            }
        });
        add(selected);

        final CommandLink<EmployeeBean> create = new CommandLink<EmployeeBean>(
                "employee_create",
                new EmployeeBeanFactory(group),
                commands.<EmployeeBean>reserve("employee.edit")
        );
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
                commands.<EmployeesGroup>reserve("employee.remove")
        );

        removeItem.add(remove);

        group.addObservers(total, selected, removeItem);
    }

}
