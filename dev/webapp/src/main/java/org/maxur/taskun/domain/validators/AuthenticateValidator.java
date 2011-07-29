package org.maxur.taskun.domain.validators;

import org.maxur.taskun.domain.Entity;
import org.maxur.taskun.domain.user.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/29/11
 */
public class AuthenticateValidator implements ConstraintValidator<Authenticated, Entity> {

    @Override
    public void initialize(Authenticated constraintAnnotation) {
    }

    @Override
    public boolean isValid(Entity value, ConstraintValidatorContext context) {
        if (value instanceof User) {
            User user = (User) value;
            return null != user.getPassword() && user.getPassword().equals(user.getUserName()); //Stub
        }
        return false;
    }
}
