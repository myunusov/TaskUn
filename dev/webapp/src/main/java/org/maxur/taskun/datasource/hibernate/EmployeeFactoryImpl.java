package org.maxur.taskun.datasource.hibernate;

import org.maxur.taskun.domain.AbstractEmployee;
import org.maxur.taskun.domain.EmployeeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * The employee factory implementation (for hibernate data source).
 * @see org.maxur.taskun.domain.EmployeeFactory
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Service
public class EmployeeFactoryImpl implements EmployeeFactory {

    /**
     * The logger.
     */
    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeFactoryImpl.class);

    /**
     * @see org.maxur.taskun.domain.EmployeeFactory#create()
     * @return The Employee implementation.
     *
     */
    @Override
    public final AbstractEmployee create() {
        LOGGER.debug("New Employee was created ");
        return new EmployeeImpl();
    }
}
