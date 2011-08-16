package org.maxur.taskun.view;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.view.commands.CommandsSuite;
import org.maxur.taskun.view.config.ConfigSuite;
import org.maxur.taskun.view.model.ModelSuite;
import org.maxur.taskun.view.pages.PagesSuite;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        ModelSuite.class,
        CommandsSuite.class,
        TaskunApplicationTest.class,
        PagesSuite.class,
        ConfigSuite.class
})
public class ViewSuite {
}
