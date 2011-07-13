package org.maxur.taskun.view.components;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
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

    /**
     * Serial Version UID.
     */
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
    protected void populateItem(final ListItem<Employee> listItem) {
        Employee item = listItem.getModelObject();
        final Label title = new Label("employee_title", item.getTitle());
        listItem.add(title);
        // TODO rewrite to resource reference use
        final Image image = new Image("employee_img");
        switch (item.getGender()) {
            case MALE:
                image.add(new SimpleAttributeModifier("src", "/images/User_male.png"));
                break;
            case FEMALE:
                image.add(new SimpleAttributeModifier("src", "/images/User_female.png"));
                break;
            default:
                image.add(new SimpleAttributeModifier("src", "/images/User_black.png"));
                break;
        }
        listItem.add(image);
    }
}
