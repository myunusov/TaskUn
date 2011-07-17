package org.maxur.taskun.view.pages;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.model.EmployeeBean;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
 * Panel for displaying the employees list.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class EmployeeListPanel extends Panel {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 2704484818965768549L;

    /**
     * The Employee panel's constructor.
     *
     * @param id The Employee List Panel identifier.
     * @param group The displaying group.
     */
    public EmployeeListPanel(final String id, final EmployeesGroup group) {
        super(id);
            //TODO MY must be exclude string constants
        add(new Label("employee_list_title", "Коллеги"));
        final EmployeesView employees = new EmployeesView("employees", group);
        add(employees);
        add(new HiddenPagingNavigator("navigator", employees));
    }

    /**
     * The class is Wicket View of Employees.
     *
     */
    public static class EmployeesView extends PageableListView<EmployeeBean> {

        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = -1086603569385693092L;

        private static final int ROWS_PER_PAGE = 5;

        /**
         * Constructs new EmployeesView instance.
         *
         * @param id        The View's identifier.
         * @param employees The Employees List for represent on web.
         */
        public EmployeesView(
                final String id,
                final EmployeesGroup employees
        ) {
            super(id, employees.getAll(), ROWS_PER_PAGE);
        }

        /**
         * Populate a given employee List Item .
         *
         * @param listItem The item to populate
         * @see org.apache.wicket.markup.html.list.ListView#populateItem(
         *org.apache.wicket.markup.html.list.ListItem)
         */
        @Override
        protected void populateItem(final ListItem<EmployeeBean> listItem) {
            listItem.add(new EmployeeLink(listItem.getModel()));
            final EmployeeBean item = listItem.getModelObject();
            listItem.add(new Label("employee_title", item.getTitle()));
        }

        private class EmployeeLink extends Link<EmployeeBean> {

            /**
             * Serial Version UID.
             */
            private static final long serialVersionUID = -2095950426753867925L;

            public EmployeeLink(IModel<EmployeeBean> model) {
                super("select", model);
                final EmployeeBean item = getModelObject();
                if (item.isSelected()) {
                    add(new SimpleAttributeModifier("class", "employee_selected"));
                }
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
                add(image);
            }

            @Override
            public void onClick() {
                EmployeeBean selected = getModelObject();
                selected.select();
            }
        }
    }
}
