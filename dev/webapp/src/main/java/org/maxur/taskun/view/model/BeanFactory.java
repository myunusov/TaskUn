package org.maxur.taskun.view.model;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.Model;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
public abstract class BeanFactory<T extends Serializable> extends Model<T> {

    private static final long serialVersionUID = 4366984104829091768L;

    protected BeanFactory() {
        InjectorHolder.getInjector().inject(this);
    }

    @Override
    public abstract T getObject();
}
