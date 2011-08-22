package org.maxur.commons;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.commons.domain.CommonsDomainSuite;
import org.maxur.commons.service.impl.CommonsServiceSuite;
import org.maxur.commons.validator.CommonsValidatorSuite;

@RunWith(Suite.class)@Suite.SuiteClasses({
        CommonsDomainSuite.class,
        CommonsServiceSuite.class,
        CommonsValidatorSuite.class
})
public class AllCommonsSuite {
}
