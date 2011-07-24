package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class CommandTest {

    private Command command;

    @Before
    public void setUp() throws Exception {
        command = new Command() {
            @Override
            public void execute(AjaxRequestTarget target, IModel iModel) {
            }
        };
    }

    @Test
    public void testClone() throws Exception {
        final Command clone = command.clone();
        Assert.assertFalse(clone == command);
    }
}
