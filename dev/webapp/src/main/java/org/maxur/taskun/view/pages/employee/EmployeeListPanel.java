package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.components.CommandLink;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.components.HighlightLabel;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.EmployeeBean;
import org.maxur.taskun.view.model.EmployeesGroup;

import java.io.Serializable;
import java.util.List;

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
     * @param id             The Employee List Panel identifier.
     * @param model          The displaying group model.
     * @param commands       The Commands repository
     */
    public EmployeeListPanel(
            final String id,
            final IModel<EmployeesGroup> model,
            final CommandRepository commands
    ) {
        super(id);
        final EmployeesGroup group = model.getObject();
        add(new Label("employee_list_title", new ResourceModel("list.employee.title")));

        final AjaxMarkupContainer<EmployeesGroup> mark =
                new AjaxMarkupContainer<EmployeesGroup>("employee_list", model);
        add(mark);
        group.addObserver(mark);
        final EmployeesView employees =
                new EmployeesView("employees", new EmployeeListModel(group), commands, ROWS_PER_PAGE);
        mark.add(employees);
        mark.add(new HiddenPagingNavigator("navigator", employees));
    }


    private static class EmployeeListModel extends Model {

        private static final long serialVersionUID = -808007446522845261L;

        private final EmployeesGroup group;

        public EmployeeListModel(final EmployeesGroup group) {
            this.group = group;
        }

        @Override
        public Serializable getObject() {
            return (Serializable) group.getAll();
        }
    }


    /**
     * The class is Wicket View of Employees.
     */
    public static class EmployeesView extends PageableListView<EmployeeBean> {

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
                final IModel<? extends List<? extends EmployeeBean>> model,
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
        protected void populateItem(final ListItem<EmployeeBean> listItem) {
            final EmployeeBean employee = listItem.getModelObject();

            final CommandLink select = new CommandLink<EmployeeBean>(
                    "employee_select",
                    listItem.getModel(),
                    commands,
                    "employee.select"
            );
            listItem.add(select);

            if (employee.isSelected()) {
                select.add(new SimpleAttributeModifier("class", "employee_selected"));
            }
            // TODO rewrite to resource reference use
            final Image image = new Image("employee_img");
            image.add(new SimpleAttributeModifier("src", "/images/" + employee.getImageName()));
            select.add(image);

            final CommandLink edit = new CommandLink<EmployeeBean>(
                    "employee_edit",
                    listItem.getModel(),
                    commands,
                    "employee.edit"
            );
            listItem.add(edit);

            final HighlightLabel title = new HighlightLabel("employee_title", new Model<String>() {
                @Override
                public String getObject() {
                    return employee.getTitle();
                }
            });
            edit.add(title);
            employee.addObserver(title);
        }
    }
}
