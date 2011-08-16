package org.maxur.taskun.view.pages.admin;

import org.apache.wicket.markup.html.WebPage;
import org.junit.Test;
import org.maxur.taskun.view.model.employee.EmployeeHelper;
import org.maxur.taskun.view.pages.AbstractPageTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
public class AdminPageTest extends AbstractPageTest {


    @Override
    protected void start() {
    }

    @Override
    protected Class<? extends WebPage> getPageClass() {
        return AdminPage.class;
    }

    @Test
    public void testBasePageComponents() {
        context.checking(EmployeeHelper.makeExpectations(controller, 0));
        super.start();
        tester.assertComponent("user_panel", UserPanel.class);
        tester.assertComponent("admin_panel", AdminPanel.class);
    }

    @Test
    public void testPageBasicRender() {
        context.checking(EmployeeHelper.makeExpectations(controller, 0));
        super.start();
        tester.assertRenderedPage(getPageClass());
    }

}
