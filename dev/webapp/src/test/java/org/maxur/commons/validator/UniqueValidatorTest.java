package org.maxur.commons.validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.domain.Entity;
import org.maxur.commons.domain.EntityRepository;

import javax.validation.ConstraintValidatorContext;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/21/11
 */
public class UniqueValidatorTest {

    private UniqueValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new UniqueValidator();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldBeGetPropertiesFromUniqueAnnotationOnInitialize() throws Exception {
        final Unique unique = mock(Unique.class);
        validator.initialize(unique);
        verify(unique).properties();
    }

    @Test
    public void shouldBeValidIfEntityNotExistInRepository() throws Exception {
        final Entity entity = mock(Entity.class);
        final EntityRepository repository = mock(EntityRepository.class);
        validator.setRepository(repository);
        when(repository.isExist(entity, null)).thenReturn(false);
        final boolean valid = validator.isValid(entity, mock(ConstraintValidatorContext.class));
        verify(repository).isExist(entity, null);
        assertTrue("Result Should Be IsValid If Not Exists In Repository", valid);
    }

    @Test
    public void shouldBeInvalidIfEntityNotExistInRepository() throws Exception {
        final Entity entity = mock(Entity.class);
        final EntityRepository repository = mock(EntityRepository.class);
        validator.setRepository(repository);
        when(repository.isExist(entity, null)).thenReturn(true);
        final boolean valid = validator.isValid(entity, mock(ConstraintValidatorContext.class));
        verify(repository).isExist(entity, null);
        assertFalse("Result Should Be Invalid If Exists In Repository", valid);
    }

    @Test(expected = AssertionError.class)
    public void shouldBeThrowsExceptionIfRepositoryIsNotSets() throws Exception {
        final Entity entity = mock(Entity.class);
        validator.isValid(entity, mock(ConstraintValidatorContext.class));
    }

}
