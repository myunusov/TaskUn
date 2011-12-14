package org.maxur.commons.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verifyZeroInteractions;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
@RunWith(MockitoJUnitRunner.class)
public class AllSpecificationTest {

    @Mock
    private Entity dummy;

    @Test
    public void testIsSatisfiedBy() throws Exception {
        Specification<Entity> specification = new AllSpecification<Entity>();
        final boolean result = specification.isSatisfiedBy(dummy);
        junit.framework.Assert.assertTrue("The All Specification returns false", result);
        verifyZeroInteractions(dummy);
    }
}
