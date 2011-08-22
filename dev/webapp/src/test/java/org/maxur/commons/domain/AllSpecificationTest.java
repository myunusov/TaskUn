package org.maxur.commons.domain;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.domain.AllSpecification;
import org.maxur.commons.domain.Entity;
import org.maxur.commons.domain.Specification;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public class AllSpecificationTest {

    private Entity dummy;
    private Mockery context;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {
            {
                setImposteriser(ClassImposteriser.INSTANCE);
                dummy = mock(Entity.class);
            }
        };
    }

    @Test
    public void testIsSatisfiedBy() throws Exception {
        context.checking(new Expectations() {{
            never(dummy);
        }});
        Specification<Entity> specification = new AllSpecification<Entity>();
        final boolean result = specification.isSatisfiedBy(dummy);
        junit.framework.Assert.assertTrue("The All Specification returns false", result);
        context.assertIsSatisfied();
    }
}
