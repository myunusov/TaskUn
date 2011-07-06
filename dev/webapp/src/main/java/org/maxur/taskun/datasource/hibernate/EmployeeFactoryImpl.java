package org.maxur.taskun.datasource.hibernate;

import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.EmployeeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Service
public class EmployeeFactoryImpl implements EmployeeFactory {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeFactoryImpl.class);

    @Override
    public Employee create() {
        logger.debug("New Employee was created ");
        return new EmployeeImpl();
    }
}
