package org.maxur.taskun.domain.validators;

import org.maxur.taskun.domain.SecurityService;
import org.maxur.taskun.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/29/11
 */
public class AuthenticateValidator implements ConstraintValidator<Authenticated, User> {

    private SecurityService securityService;

    @Autowired
    public void setSecurityService(final SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void initialize(Authenticated constraintAnnotation) {
    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        return securityService.authentication(value);
    }

}
