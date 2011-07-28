package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public class CommandRepositoryTest {

    private JUnit4Mockery context;

    private Command<Bean> command;
    private CommandRepository repository;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        command = (Command<Bean>) context.mock(Command.class, "command");
        repository = new CommandRepository();

    }

    @Test
    public void testGet() throws Exception {
        repository.persist("1", command);
        context.checking(new Expectations() {{
            oneOf(command).clone();
        }});
        repository.get("1");
        context.assertIsSatisfied();
    }

    @Test
    public void testGetSame() throws Exception {
        final MyCommand command1 = new MyCommand();
        repository.persist("2", command1);
        final Command<Bean> command2 = repository.get("2");
        Assert.assertTrue(command1.getClass() == command2.getClass());
        Assert.assertFalse(command1 == command2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNone() throws Exception {
        final Command<Bean> command2 = repository.get("3");
    }

    private static class MyCommand extends Command<Bean> {
        @Override
        public void execute(AjaxRequestTarget target, Bean model) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
