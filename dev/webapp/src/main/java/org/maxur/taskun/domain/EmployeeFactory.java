package org.maxur.taskun.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Service
public class EmployeeFactory {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeFactory.class);

    public Employee create() {
        logger.debug("New Employee was created ");
        return new Employee();
    }
}
