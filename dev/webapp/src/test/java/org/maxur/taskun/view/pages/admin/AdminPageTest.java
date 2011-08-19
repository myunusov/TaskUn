package org.maxur.taskun.view.pages.admin;

import org.apache.wicket.markup.html.WebPage;
import org.junit.Test;
import org.maxur.taskun.services.ControllerExpectationBuilder;
import org.maxur.taskun.view.pages.AbstractPageTest;
import org.maxur.taskun.view.pages.home.HomePage;

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
        context.checking(new ControllerExpectationBuilder(application.controller).build());
        application.setRole("ROLE_ADMIN");
        super.start();
        tester.assertComponent("user_panel", UserPanel.class);
        tester.assertComponent("admin_panel", AdminPanel.class);
    }

    @Test
    public void testPageBasicRenderWithoutAdminRole() {
        super.start();
        tester.assertRenderedPage(HomePage.class);
    }

    @Test
    public void testPageBasicRender() {
        application.setRole("ROLE_ADMIN");
        context.checking(new ControllerExpectationBuilder(application.controller).build());
        super.start();
        tester.assertRenderedPage(getPageClass());
    }

}
