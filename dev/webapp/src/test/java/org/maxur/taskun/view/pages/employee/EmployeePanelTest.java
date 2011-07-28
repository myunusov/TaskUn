package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.view.model.employee.EmployeeBean;
import org.maxur.taskun.view.pages.AbstractPanelTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
@RunWith(JMock.class)
public class EmployeePanelTest extends AbstractPanelTest {

    @Override
    protected void startPanel() {
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(String panelId) {
                return new EmployeePanel(panelId, new EmployeeBean(null, dummyEmployee){}, null, null);
            }
        });
    }

    @Test
    public void testPanelBasicRender() {
        tester.assertComponent("panel:employee_form", Form.class);
    }

    @Test
    public void testWicketPanel() throws Exception {
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

}
