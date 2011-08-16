package org.maxur.taskun.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.maxur.taskun.domain.employee.AbstractEmployeeTest;
import org.maxur.taskun.domain.validators.ValidatorsSuite;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */
@RunWith(Suite.class)@Suite.SuiteClasses({
        AbstractEmployeeTest.class,
        AbstractEntityTest.class,
        AllSpecificationTest.class,
        ValidatorsSuite.class
})
public class DomainSuite {
}
