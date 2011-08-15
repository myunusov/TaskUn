package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.junit.Test;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class EmployeePanelTest extends AbstractPanelTest {

    static private Employee dummy = new AbstractEmployee() {
        private static final long serialVersionUID = 3908424889025108375L;
    };

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new EmployeePanel(panelId, new EmployeeBean(null, dummy) {
        }, null, null);
    }

    @Test
    public void testPanelBasicRender() {
        tester.assertComponent("panel:employee_form", Form.class);
    }

}
