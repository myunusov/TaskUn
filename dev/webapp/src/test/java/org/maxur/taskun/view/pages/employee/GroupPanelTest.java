package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.panel.Panel;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.components.AjaxMarkupContainer;
import org.maxur.taskun.view.model.employee.EmployeesGroup;
import org.maxur.taskun.view.pages.AbstractPanelTest;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@RunWith(JMock.class)
public class GroupPanelTest extends AbstractPanelTest {

    static private Employee dummy = new AbstractEmployee() {
        private static final long serialVersionUID = 3908424889025108375L;
    };

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new GroupPanel(panelId, new EmployeesGroup(), new CommandRepositoryImpl());
    }

    @Override
    protected void start() {
    }

    private void startPanel(final int numberOfItems) {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(numberOfItems, dummy)));
        }});
        super.start();
    }

    @Test
    public void testRemoveOnEmployeesIsEmpty() {
        startPanel(0);
        tester.assertInvisible("panel:remove_item");
    }

    @Test
    public void testRemoveOnEmployeesIsNotEmpty() {
        startPanel(1);
        final Component panel = tester.getComponentFromLastRenderedPage("panel");
        final EmployeesGroup model = (EmployeesGroup) panel.getDefaultModel();
        model.getObject().get(0).select(null);
        tester.assertComponent("panel:remove_item", AjaxMarkupContainer.class);
    }

}
