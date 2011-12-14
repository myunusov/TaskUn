package org.maxur.commons.validator;

import org.maxur.commons.domain.Entity;
import org.maxur.commons.domain.EntityBuilder;
import org.maxur.commons.domain.EntityRepository;
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
     * @see org.maxur.commons.domain.Repository
     */
    private EntityRepository repository;

    public void initialize(final Unique annotation) {
        this.fields = annotation.properties();
    }

    @Override
    public boolean isValid(final Entity value, final ConstraintValidatorContext context) {
        assert (null != repository) : "The EntityRepository is not be injected in UniqueValidator class";
        if (value.isNew()) {
            return !repository.isExistAs((EntityBuilder) value, fields);
        } else {
            return !repository.isExist(value, fields);
        }
    }

    @Autowired()
    @Qualifier(value = "entityRepository")
    public void setRepository(final EntityRepository repository) {
        this.repository = repository;
    }
}