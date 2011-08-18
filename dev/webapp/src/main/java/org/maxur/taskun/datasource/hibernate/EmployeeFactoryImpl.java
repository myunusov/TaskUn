package org.maxur.taskun.datasource.hibernate;

import org.maxur.taskun.datasource.DataSourceNotifier;
import org.maxur.commons.domain.Factory;
import org.maxur.taskun.domain.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The employee factory implementation (for hibernate data source).
 * @see org.maxur.commons.domain.Factory
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Service
public class EmployeeFactoryImpl implements Factory<Employee> {

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

    /**
     * @see org.maxur.commons.domain.Factory#create()
     *
     * @return The Employee implementation.
     *
     */
    @Override
    public final Employee create() {
        notifier.notifyEmployeeCreate(this.getClass());
        return new EmployeeImpl();
    }
}
