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
import org.maxur.taskun.services.ServicesTests;
import org.maxur.taskun.view.ViewTests;

@RunWith(Suite.class)@Suite.SuiteClasses({
        DatasourceTests.class,
        DomainTests.class,
        ServicesTests.class,
        ViewTests.class
})
public class AllUnitTests {
}
