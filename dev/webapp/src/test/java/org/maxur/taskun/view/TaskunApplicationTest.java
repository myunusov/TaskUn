package org.maxur.taskun.view;

import org.apache.wicket.Page;
import org.junit.Assert;
import org.junit.Test;
import org.maxur.taskun.view.pages.HomePage;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
public class TaskunApplicationTest {

    @Test
    public void testGetHomePage() throws Exception {
        final TaskunApplication application = new TaskunApplication();
        final Class<? extends Page> homePage = application.getHomePage();
        Assert.assertTrue(homePage.equals(HomePage.class));
    }
}
