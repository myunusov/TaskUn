package org.maxur.taskun.view.components;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public class Jsr303PropertyValidator<T, Z> implements INullAcceptingValidator<Z> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5693091927064082562L;

    @SpringBean
    protected Validator validator;

    protected String propertyName;

    protected Class<T> beanType;

    public Jsr303PropertyValidator(final Class<T> clazz, final String propertyName) {
        this.propertyName = propertyName;
        this.beanType = clazz;
        injectDependencies();
    }

    private void injectDependencies() {
        InjectorHolder.getInjector().inject(this);
    }

    @Override
    public void validate(final IValidatable<Z> value) {
        Set<ConstraintViolation<T>> res =
                this.validator.validateValue(this.beanType, this.propertyName, value.getValue());
        for (ConstraintViolation<T> vio : res) {
            value.error(new ValidationError().setMessage(vio.getMessage()));
        }
    }

}

