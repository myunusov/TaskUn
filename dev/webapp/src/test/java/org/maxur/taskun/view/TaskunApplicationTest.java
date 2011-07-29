package org.maxur.taskun.view;

import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.view.pages.employee.EmployeePage;

import java.util.Locale;


/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
public class TaskunApplicationTest {

    private TaskunApplication application;

    private Mockery context;

    @Before
    public void setUp() throws Exception  {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        application = new TaskunApplication();
    }

    @Test
    public void testGetHomePage() throws Exception {
        application.setHomePage(EmployeePage.class);
        final Class<? extends Page> homePage = application.getHomePage();
        Assert.assertTrue(EmployeePage.class.equals(homePage));
    }

    @Test
    public void testCreateUserSession() throws Exception {
        final Request request = context.mock(Request.class);
        context.checking(new Expectations() {{
            oneOf(request).getLocale(); will(returnValue(Locale.US));
        }});

        final Session session = application.newSession(request, null);
        Assert.assertTrue(session instanceof UserSession);
    }
}
