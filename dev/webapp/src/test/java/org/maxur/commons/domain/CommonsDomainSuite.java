package org.maxur.commons.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        AllSpecificationTest.class,
        BaseEntityTest.class,
        EntityBuilderTest.class
})
public class CommonsDomainSuite {
}
