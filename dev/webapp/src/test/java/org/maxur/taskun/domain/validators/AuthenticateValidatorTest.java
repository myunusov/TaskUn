package org.maxur.taskun.domain.validators;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.AbstractMockTest;
import org.maxur.taskun.domain.SecurityService;
import org.maxur.taskun.domain.user.User;

import javax.validation.ConstraintValidatorContext;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/16/11
 */
public class AuthenticateValidatorTest extends AbstractMockTest {

    private AuthenticateValidator validator;

    private ConstraintValidatorContext validatorContext;

    private User entity;

    private SecurityService securityService;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        securityService = context.mock(SecurityService.class);
        validator = new AuthenticateValidator();
        validator.setSecurityService(securityService);
        validatorContext = context.mock(ConstraintValidatorContext.class);
        entity = context.mock(User.class);
    }


    @Test
    public void testInitialize() throws Exception {
        final Authenticated authenticated = context.mock(Authenticated.class);
        context.checking(new Expectations() {{
            never(authenticated);
        }});
        validator.initialize(authenticated);
        context.assertIsSatisfied();
    }

    @Test
    public void testIsValidOnValidCase() throws Exception {
        context.checking(new Expectations() {{
            allowing(entity).getUserName(); will(returnValue("A"));
            allowing(entity).getPassword(); will(returnValue("A"));
            oneOf(securityService).authentication(entity); will(returnValue(true));
        }});
        final boolean valid = validator.isValid(entity, validatorContext);
        Assert.assertEquals(true, valid);
        context.assertIsSatisfied();
    }

    @Test
    public void testIsValidOnInvalidCase() throws Exception {
        context.checking(new Expectations() {{
            allowing(entity).getUserName(); will(returnValue("B"));
            allowing(entity).getPassword(); will(returnValue("B"));
            oneOf(securityService).authentication(entity); will(returnValue(false));
        }});
        final boolean valid = validator.isValid(entity, validatorContext);
        Assert.assertEquals(false, valid);
        context.assertIsSatisfied();
    }

}
