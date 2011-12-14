package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.Model;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
@RunWith(JMock.class)
public class BatchCommandTest {

    private Mockery context;

    private AjaxRequestTarget target;
    private Command command1;
    private Command command2;
    private Bean mockModel;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        command1 = context.mock(Command.class, "command1");
        command2 = context.mock(Command.class, "command2");
        mockModel = context.mock(Bean.class, "mockModel");
        target = context.mock(AjaxRequestTarget.class);
    }

    @Test
    public void testExecute() throws Exception {
        final BatchCommand command = new BatchCommand(command1, command2);
        final Sequence sequence = context.sequence("sequence");
        context.checking(new Expectations() {{
            oneOf(command1).execute(target, mockModel); inSequence(sequence);
            oneOf(command2).execute(target, mockModel); inSequence(sequence);
        }});
        command.execute(target, mockModel);
        context.assertIsSatisfied();
    }
}
