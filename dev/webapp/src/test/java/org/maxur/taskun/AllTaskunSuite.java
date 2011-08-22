package org.maxur.taskun;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.datasource.DatasourceSuite;
import org.maxur.taskun.domain.DomainSuite;
import org.maxur.taskun.services.ServicesSuite;
import org.maxur.taskun.view.ViewSuite;

@RunWith(Suite.class)@Suite.SuiteClasses({
        DatasourceSuite.class,
        DomainSuite.class,
        ServicesSuite.class,
        ViewSuite.class
})
public class AllTaskunSuite {
}
