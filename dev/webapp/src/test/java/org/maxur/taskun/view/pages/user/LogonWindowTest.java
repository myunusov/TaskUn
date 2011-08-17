package org.maxur.taskun.view.pages.user;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.jmock.Expectations;
import org.junit.Test;
import org.maxur.taskun.view.commands.CommandRepositoryImpl;
import org.maxur.taskun.view.pages.AbstractWicketTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/17/11
 */
public class LogonWindowTest extends AbstractWicketTest {

    private LogonWindow window;

    @Override
    protected void start() {
        CommandRepositoryImpl repository = context.mock(CommandRepositoryImpl.class);
        context.checking(new Expectations() {{
        }});
        window = new LogonWindow("window", repository);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testShow() throws Exception {
        final AjaxRequestTarget target = context.mock(AjaxRequestTarget.class);
        window.show(target);
    }


}
