package org.maxur.taskun.datasource.hibernate;

import org.maxur.taskun.domain.Factory;
import org.maxur.taskun.domain.employee.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * The employee factory implementation (for hibernate data source).
 * @see org.maxur.taskun.domain.Factory
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Service
public class EmployeeFactoryImpl implements Factory<Employee> {

    /**
     * The logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeFactoryImpl.class);

    /**
     * @see org.maxur.taskun.domain.Factory#create()
     *
     * @return The Employee implementation.
     *
     */
    @Override
    public final Employee create() {
        LOGGER.debug("New Employee was created ");
        return new EmployeeImpl();
    }
}
