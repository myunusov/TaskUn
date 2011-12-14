package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Test;
import org.maxur.taskun.view.model.employee.EmployeeBuilder;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class EmployeePanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new EmployeePanel(panelId, EmployeeBuilder.makeEmployeeBean(), null, null);
    }

    @Test
    public void testPanelBasicRender() {
        tester.assertComponent("panel:employee_form", Form.class);
    }

}
