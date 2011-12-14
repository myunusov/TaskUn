package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.components.CommandLink;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.components.HighlightLabel;
import org.maxur.taskun.view.components.UpdatedImage;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.ModelList;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.model.employee.EmployeesGroup;

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
    private static final int ROWS_PER_PAGE = 15;    //TODO Must be configurable

    /**
     * The Employee panel's constructor.
     *
     * @param id       The Employee List Panel identifier.
     * @param model    The displaying group model.
     * @param commands The Commands repository
     */
    public EmployeeListPanel(
            final String id,
            final EmployeesGroup model,
            final CommandRepository commands
    ) {
        super(id);
        final AjaxMarkupContainer<ModelList<EmployeeBean>> mark =
                new AjaxMarkupContainer<ModelList<EmployeeBean>>("employee_list", model);
        add(mark);
        model.addObserver(mark);
        final EmployeesView employees =
                new EmployeesView("employees", model, commands, ROWS_PER_PAGE);
        mark.add(employees);
        mark.add(new HiddenPagingNavigator("navigator", employees));
    }

    /**
     * The class is Wicket View of Employees.
     */
    public static class EmployeesView extends DataView<EmployeeBean> {

        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = -1086603569385693092L;

        /**
         * The Commands repository
         */
        private final CommandRepository commands;


        /**
         * Constructs new EmployeesView instance.
         *
         * @param id          The View's identifier.
         * @param model       The Employees Group model.
         * @param commands    The Commands repository
         * @param rowsPerPage Number of rows to show on a page
         */
        public EmployeesView(
                final String id,
                final IDataProvider<EmployeeBean> model,
                final CommandRepository commands,
                final int rowsPerPage
        ) {
            super(id, model, rowsPerPage);
            this.commands = commands;
        }

        /**
         * Populate a given employee List Item .
         *
         * @param listItem The item to populate
         * @see org.apache.wicket.markup.html.list.ListView#populateItem(
         *org.apache.wicket.markup.html.list.ListItem)
         */
        @Override
        protected void populateItem(Item<EmployeeBean> listItem) {
            final EmployeeBean employee = listItem.getModelObject();

            final CommandLink<EmployeeBean> select = new CommandLink<EmployeeBean>(
                    "employee_select",
                    employee,
                    commands.<EmployeeBean>reserve("employee.select")
            );
            listItem.add(select);

            if (employee.isSelected()) {
                select.add(new SimpleAttributeModifier("class", "employee_selected"));
            }
            // TODO rewrite to resource reference use
            final UpdatedImage image = new UpdatedImage("employee_img", new Model<String>() {
                @Override
                public String getObject() {
                    return employee.getImageName();
                }
            });

            select.add(image);

            final CommandLink<EmployeeBean> edit = new CommandLink<EmployeeBean>(
                    "employee_edit",
                    employee,
                    commands.<EmployeeBean>reserve("employee.edit")
            );
            listItem.add(edit);

            final HighlightLabel title = new HighlightLabel("employee_title", new Model<String>() {
                @Override
                public String getObject() {
                    return employee.getTitle();
                }
            });
            edit.add(title);
            employee.addObservers(title, image);
        }

    }

}
