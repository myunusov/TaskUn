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
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.maxur.taskun.view.components.AjaxChangeManager;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.model.EmployeeBean;
import org.maxur.taskun.view.model.EmployeesGroup;

import java.io.Serializable;

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
     * @param employeeWindow
     * @param changeManager  The Group panel.
     */
    public EmployeeListPanel(
            final String id,
            final IModel<EmployeesGroup> model,
            final EmployeeWindow employeeWindow,
            final AjaxChangeManager changeManager
    ) {
        super(id);
        final EmployeesGroup group = model.getObject();
        add(new Label("employee_list_title", new ResourceModel("list.employee.title")));

        final AjaxMarkupContainer mark = new AjaxMarkupContainer("employee_list", model);
        add(mark);
        changeManager.addComponent(mark);
        final EmployeesView employees =
                new EmployeesView("employees", group, changeManager, employeeWindow, ROWS_PER_PAGE);
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
        private final AjaxChangeManager groupChangeManager;

        private final EmployeeWindow editDialog;

        /**
         * Constructs new EmployeesView instance.
         *
         * @param id            The View's identifier.
         * @param group         The Employees Group.
         * @param changeManager The Group panel.
         * @param editDialog
         * @param rowsPerPage   Number of rows to show on a page
         */
        public EmployeesView(
                final String id,
                final EmployeesGroup group,
                final AjaxChangeManager changeManager,
                final EmployeeWindow editDialog,
                final int rowsPerPage
        ) {
            super(id, new EmployeeListModel(group), rowsPerPage);
            this.group = group;
            this.groupChangeManager = changeManager;
            this.editDialog = editDialog;
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
            listItem.add(new EmployeeLink("employee_select", listItem.getModel()));
            final EmployeeBean item = listItem.getModelObject();
            final EmployeeEditLink link =
                    new EmployeeEditLink("employee_edit", listItem.getModel(), editDialog);
            listItem.add(link);
            link.add(new Label("employee_title", item.getTitle()));
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

        private static class EmployeeEditLink extends AjaxFallbackLink<EmployeeBean> {

            /**
             * Serial Version UID.
             */
            private static final long serialVersionUID = -8095364080994940288L;

            private final EmployeeWindow editDialog;

            public EmployeeEditLink(
                    final String id,
                    final IModel<EmployeeBean> model,
                    EmployeeWindow editDialog
            ) {
                super(id, model);
                this.editDialog = editDialog;
            }

            @Override
            public void onClick(final AjaxRequestTarget target) {
                editDialog.show(target, getModelObject());
            }
        }

        private class EmployeeLink extends AjaxFallbackLink<EmployeeBean> {

            /**
             * Serial Version UID.
             */
            private static final long serialVersionUID = -2095950426753867925L;

            public EmployeeLink(final String id, final IModel<EmployeeBean> model) {
                super(id, model);
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
                groupChangeManager.update(target);
            }


        }
    }
}
