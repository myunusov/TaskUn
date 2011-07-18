package org.maxur.taskun.view.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.components.Mark;
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
     * Number of rows to show on a page
     */
    private static final int ROWS_PER_PAGE = 5;

    /**
     * The Employee panel's constructor.
     *
     * @param id       The Employee List Panel identifier.
     * @param model    The displaying group model.
     * @param observer The Group panel.
     */
    public EmployeeListPanel(
            final String id,
            final IModel<EmployeesGroup> model,
            final AjaxObserver observer
    ) {
        super(id);
        final EmployeesGroup group = model.getObject();
        //TODO MY must be exclude string constants
        add(new Label("employee_list_title", "Коллеги"));
        final Mark<EmployeesGroup> mark = new Mark<EmployeesGroup>("employee_list_link", group);
        add(mark);
        observer.addComponent(mark);
        final EmployeesView employees =
                new EmployeesView("employees", group, observer, ROWS_PER_PAGE);

        employees.setReuseItems(false);
        mark.add(employees);
        mark.add(new HiddenPagingNavigator("navigator", employees));
    }



    /**
     * The class is Wicket View of Employees.
     */
    public static class EmployeesView extends PageableListView<EmployeeBean> {

        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = -1086603569385693092L;

        private final EmployeesGroup group;
        /**
         * The Group panel.
         */
        private final AjaxObserver groupObserver;

        /**
         * Constructs new EmployeesView instance.
         *
         * @param id          The View's identifier.
         * @param group       The Employees Group.
         * @param observer    The Group panel.
         * @param rowsPerPage Number of rows to show on a page
         */
        public EmployeesView(
                final String id,
                final EmployeesGroup group,
                final AjaxObserver observer,
                final int rowsPerPage
        ) {
            super(id, group.getAll(), rowsPerPage);
            this.group = group;
            this.groupObserver = observer;
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

        private class EmployeeLink extends AjaxFallbackLink<EmployeeBean> {

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
            public void onClick(AjaxRequestTarget target) {
                EmployeeBean selected = getModelObject();
                selected.select();
                groupObserver.update(target);
            }
        }
    }
}
