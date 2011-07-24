package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
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
public class AjaxChangeManagerTest {

    private Mockery context;

    private AjaxChangeManager manager;
    private AjaxObserver observer1;
    private AjaxObserver observer2;
    private AjaxRequestTarget target;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        manager = new AjaxChangeManager();
        observer1 = context.mock(AjaxObserver.class, "observer1");
        observer2 = context.mock(AjaxObserver.class, "observer2");
        target = context.mock(AjaxRequestTarget.class);
    }

    @Test
    public void testUpdate() throws Exception {
        final Sequence sequence = context.sequence("sequence");
        context.checking(new Expectations() {{
            oneOf(observer1).get();
            oneOf(observer2).get();
        }});
        manager.addComponents(observer1, observer2);
        context.checking(new Expectations() {{
            oneOf(observer1).update(target); inSequence(sequence);
            oneOf(observer2).update(target); inSequence(sequence);
        }});
        context.checking(new Expectations() {{
        }});
        manager.update(target);
        context.assertIsSatisfied();
    }

    @Test
    public void testUpdateWithoutTarget() throws Exception {
        context.checking(new Expectations() {{
            oneOf(observer1).get();
            oneOf(observer2).get();
        }});
        manager.addComponents(observer1, observer2);
        context.checking(new Expectations() {{
        }});
        context.checking(new Expectations() {{
        }});
        manager.update(null);
        context.assertIsSatisfied();
    }

    @Test
    public void testAddComponent() throws Exception {
        context.checking(new Expectations() {{
            oneOf(observer1).get();
        }});
        manager.addComponent(observer1);
        context.assertIsSatisfied();
    }

    @Test
    public void testAddComponents() throws Exception {
        context.checking(new Expectations() {{
            oneOf(observer1).get();
            oneOf(observer2).get();
        }});
        manager.addComponents(observer1, observer2);
        context.assertIsSatisfied();
    }
}
