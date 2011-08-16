package org.maxur.taskun.view.config;

import org.apache.wicket.markup.html.WebPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.pages.admin.AdminPage;
import org.maxur.taskun.view.pages.archive.ArchivePage;
import org.maxur.taskun.view.pages.employee.EmployeePage;
import org.maxur.taskun.view.pages.home.HomePage;
import org.maxur.taskun.view.pages.self.SelfPage;
import org.maxur.taskun.view.pages.task.TaskPage;

import javax.validation.MessageInterpolator;
import javax.validation.Validator;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
public class ViewConfiguratorTest {

    private ViewConfigurator viewConfigurator;

    @Before
    public void setUp() throws Exception {
        viewConfigurator = new ViewConfigurator();
    }

    @Test
    public void testHomePage() throws Exception {
        final Class<? extends WebPage> aClass = viewConfigurator.homePage();
        Assert.assertTrue(aClass.equals(HomePage.class));

    }

    @Test
    public void testMenuItems() throws Exception {
        final MenuItems items = viewConfigurator.menuItems();
        Assert.assertEquals("menu.item.main", items.get(0).getTitle());
        Assert.assertEquals(HomePage.class, items.get(0).getTargetPage());
        Assert.assertEquals("menu.item.task", items.get(1).getTitle());
        Assert.assertEquals(TaskPage.class, items.get(1).getTargetPage());
        Assert.assertEquals("menu.item.employee", items.get(2).getTitle());
        Assert.assertEquals(EmployeePage.class, items.get(2).getTargetPage());
        Assert.assertEquals("menu.item.archive", items.get(3).getTitle());
        Assert.assertEquals(ArchivePage.class, items.get(3).getTargetPage());
        Assert.assertEquals("menu.item.admin", items.get(4).getTitle());
        Assert.assertEquals(AdminPage.class, items.get(4).getTargetPage());
        Assert.assertEquals("menu.item.self", items.get(5).getTitle());
        Assert.assertEquals(SelfPage.class, items.get(5).getTargetPage());
    }

    @Test
    public void testValidator() throws Exception {
        final Validator validator = viewConfigurator.validator();
        Assert.assertNotNull(validator);
    }

    @Test
    public void testMessageInterpolator() throws Exception {
        final MessageInterpolator interpolator = viewConfigurator.messageInterpolator();
        Assert.assertNotNull(interpolator);
    }
}
