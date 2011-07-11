package org.maxur.taskun;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.datasource.DatasourceTests;
import org.maxur.taskun.domain.DomainTests;
import org.maxur.taskun.it.IntegrationTests;
import org.maxur.taskun.view.ViewTests;

@RunWith(Suite.class)@Suite.SuiteClasses({
        IntegrationTests.class,
        DatasourceTests.class,
        DomainTests.class,
        ViewTests.class
})
public class AllTestSuite {
}
