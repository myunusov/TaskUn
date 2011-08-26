package org.maxur.taskun.datasource.hibernate.employee;

import org.maxur.taskun.datasource.DataSourceNotifier;
import org.maxur.taskun.domain.employee.EmployeeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/24/11
 */
@Service
@Scope("prototype")
public class EmployeeBuilderImpl extends EmployeeBuilder<EmployeeImpl> {

    private static final long serialVersionUID = -6622708161778417536L;

    /**
     * The Notifier.
     */
    private DataSourceNotifier notifier;

    /**
     * Sets the notifier.
     * @param notifier The Notifier.
     */
    @Autowired
    public void setNotifier(DataSourceNotifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public EmployeeImpl build() {
        final EmployeeImpl employee = super.build();
        notifier.notifyEmployeeCreate(this.getClass(), employee.toString());
        return employee;
    }

    @Override
    protected EmployeeImpl make() {
        return new EmployeeImpl();
    }

}
