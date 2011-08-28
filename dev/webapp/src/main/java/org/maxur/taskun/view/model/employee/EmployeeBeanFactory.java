package org.maxur.taskun.view.model.employee;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.domain.employee.EmployeeBuilder;
import org.maxur.taskun.view.model.BeanFactory;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public class EmployeeBeanFactory extends BeanFactory<EmployeeBean> {

    private static final long serialVersionUID = 1734223566468678224L;

    /**
     * The ApplicationController bean. It's injected by Wicket IoC.
     */
    @SpringBean
    private EmployeeBuilder builder;

    private final EmployeesGroup group;

    public EmployeeBeanFactory(final EmployeesGroup group) {
        this.group = group;
    }

    @Override
    public EmployeeBean getObject() {
        return new EmployeeBean(group, builder);
    }


}
