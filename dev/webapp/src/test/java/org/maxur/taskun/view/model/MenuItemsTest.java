package org.maxur.taskun.view.model;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.view.pages.BasePage;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class MenuItemsTest {

    private MenuItems items;

    @Before
    public void setUp() throws Exception {
        items = new MenuItems(3);
    }

    @Test
    public void testAdd() throws Exception {
        items.add("1", BasePage.class, true);
        items.add("2", BasePage.class);
        items.add("3", BasePage.class);
        int i = 0;
        for (MenuItem item : items) {
            i += item.isActive() ? 1 : 0;
        }
        Assert.assertEquals(1, i);
    }

    @Test
    public void testAddWithActiveFalse() throws Exception {
        items.add("1", BasePage.class, false);
        int i = 0;
        for (MenuItem item : items) {
            i += item.isActive() ? 1 : 0;
        }
        Assert.assertEquals(0, i);
    }

    @Test
    public void testAddConcretePage() throws Exception {
        items.add("1", BasePage.class, false);
        int i = 0;
        for (MenuItem item : items) {
            Assert.assertEquals(BasePage.class, item.getTargetPage());
        }
    }

    @Test
    public void testAddConcreteValue() throws Exception {
        items.add("1", BasePage.class, false);
        int i = 0;
        for (MenuItem item : items) {
            Assert.assertEquals("1", item.getValue());
        }
    }


    @Test
    public void testAddOnlyOneActive() throws Exception {
        items.add("1", BasePage.class, true);
        items.add("2", BasePage.class, true);
        items.add("3", BasePage.class, true);
        int i = 0;
        for (MenuItem item : items) {
            i += item.isActive() ? 1 : 0;
        }
        Assert.assertEquals(1, i);
    }

    @Test
    public void testSetOnlyOneActive() throws Exception {
        items.add("1", BasePage.class, true);
        items.add("2", BasePage.class);
        items.add("3", BasePage.class);
        int i = 0;
        for (MenuItem item : items) {
            item.setActive(true);
        }
        for (MenuItem item : items) {
            i += item.isActive() ? 1 : 0;
        }
        Assert.assertEquals(1, i);
    }

    @Test
    public void testCleanActive() throws Exception {
        items.add("1", BasePage.class, true);
        items.add("2", BasePage.class);
        items.add("3", BasePage.class);
        int i = 0;
        for (MenuItem item : items) {
            item.setActive(false);
        }
        for (MenuItem item : items) {
            i += item.isActive() ? 1 : 0;
        }
        Assert.assertEquals(0, i);
    }

}
