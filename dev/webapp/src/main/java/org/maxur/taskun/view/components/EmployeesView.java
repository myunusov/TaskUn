package org.maxur.taskun.view.components;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.maxur.taskun.domain.Employee;

import java.util.List;

/**
 * The class is Wicket View of Employees.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/10/11
 */
public class EmployeesView extends ListView<Employee> {

    private static final long serialVersionUID = -1086603569385693092L;

    /**
     * Constructs new EmployeesView instance.
     *
     * @param id        The View's identifier.
     * @param employees The Employees List for represent on web.
     */
    public EmployeesView(final String id, final List<Employee> employees) {
        super(id, employees);
    }

    /**
     * Populate a given employee List Item .
     * @see org.apache.wicket.markup.html.list.ListView#populateItem(
     *                                org.apache.wicket.markup.html.list.ListItem)
     *
     * @param listItem The item to populate
     */
    @Override
    protected void populateItem(ListItem<Employee> listItem) {
        Employee item = listItem.getModelObject();
        new Label("employee_title", item.getTitle());
/*        final Link link = new MenuItemLink(item);
        listItem.add(link);
        final Mark mark = new Mark();
        link.add(mark);
        mark.add(new Label("menu_item_name", item.getValue()));*/
    }
}
