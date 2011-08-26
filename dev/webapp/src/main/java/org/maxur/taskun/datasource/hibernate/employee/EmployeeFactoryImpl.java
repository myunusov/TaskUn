package org.maxur.taskun.datasource.hibernate.employee;

import org.maxur.taskun.datasource.DataSourceNotifier;
import org.maxur.commons.domain.Factory;
import org.maxur.taskun.domain.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The employee factory implementation (for hibernate data source).
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 * @see org.maxur.commons.domain.Factory
 */
@Service
public class EmployeeFactoryImpl implements Factory<Employee> {

    /**
     * The Notifier.
     */
    private DataSourceNotifier notifier;

    /**
     * Sets the notifier.
     *
     * @param notifier The Notifier.
     */
    @Autowired
    public void setNotifier(DataSourceNotifier notifier) {
        this.notifier = notifier;
    }

    /**
     * @return The Employee implementation.
     * @see org.maxur.commons.domain.Factory#create()
     */
    @Override
    public final Employee create() {
        final EmployeeImpl employee = new EmployeeImpl();
        notifier.notifyEmployeeCreate(this.getClass(), employee.toString());
        return employee;
    }
}
