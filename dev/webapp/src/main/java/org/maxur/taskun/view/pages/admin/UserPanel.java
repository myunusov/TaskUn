package org.maxur.taskun.view.pages.admin;

import images.ImagesScope;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AbstractBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;
import org.maxur.taskun.view.components.HiddenPagingNavigator;
import org.maxur.taskun.view.components.HighlightLabel;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.model.employee.EmployeesGroup;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/29/11
 */
public final class UserPanel extends Panel {

    private static final long serialVersionUID = -4102958548466805126L;

    public UserPanel(
            final String id,
            final EmployeesGroup model,
            final CommandRepository commands
    ) {
        super(id);

        final UserView employees = new UserView("employees", model, commands, 15);   //TODO magic
        add(employees);
        add(new HiddenPagingNavigator("navigator", employees));
    }


    /**
     * The class is Wicket View of Users.
     */
    public static class UserView extends DataView<EmployeeBean> {

        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = -5036032008140925207L;

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
        public UserView(
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
            final Label label = new Label("employee_img");
            listItem.add(label);
            label.add(new BackgroundImageBehavior(new Model<String>(employee.getImageName())));
            final HighlightLabel title = new HighlightLabel("employee_title", new Model<String>(employee.getTitle()));
            listItem.add(title);
            employee.addObservers(title);
        }
    }

    static class BackgroundImageBehavior extends AbstractBehavior {

        private static final long serialVersionUID = 891804446374350749L;

        private final Model<String> model;

        public BackgroundImageBehavior(final Model<String> model) {
            this.model = model;

        }

        public void onComponentTag(Component component, ComponentTag tag) {
            final PackageResource resource = PackageResource.get(ImagesScope.class, model.getObject());
            tag.put("style", "background-image: url(" + resource.getAbsolutePath() + ")");
	    }
	}

}
