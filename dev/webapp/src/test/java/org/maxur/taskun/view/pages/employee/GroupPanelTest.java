package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.services.ControllerExpectationBuilder;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@RunWith(JMock.class)
public class GroupPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new GroupPanel(panelId, new EmployeesGroup(), new CommandRepositoryImpl());
    }

    @Override
    protected void start() {
    }

    @Test
    public void testRemoveOnEmployeesIsEmpty() {
        context.checking(new ControllerExpectationBuilder(application.controller).build());
        super.start();
        tester.assertInvisible("panel:remove_item");
    }

    @Test
    public void testRemoveOnEmployeesIsNotEmpty() {
        context.checking(new ControllerExpectationBuilder(application.controller).count(1).build());
        super.start();
        final Component panel = tester.getComponentFromLastRenderedPage("panel");
        final EmployeesGroup model = (EmployeesGroup) panel.getDefaultModel();
        model.getObject().get(0).select(null);
        tester.assertComponent("panel:remove_item", AjaxMarkupContainer.class);
    }

}
