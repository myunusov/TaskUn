package org.maxur.taskun.domain.validators;

import org.maxur.taskun.datasource.UnexpectedResultException;
import org.maxur.taskun.domain.Entity;
import org.maxur.taskun.domain.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class UniqueValidator implements ConstraintValidator<Unique, Entity> {

    private String[] fields;

    /*
     * @see org.maxur.taskun.domain.Repository
     */
    @Autowired()
    @Qualifier(value = "entityRepository")
    private EntityRepository repository;

    public void initialize(Unique annotation) {
        this.fields = annotation.properties();
    }

    @Override
    public boolean isValid(Entity value, ConstraintValidatorContext context) {
        try {
            return null == repository  || !repository.isExist(value, fields);
        } catch (UnexpectedResultException e) {
            throw new RuntimeException(e);  //TODO
        }
    }
}