package org.maxur.taskun.view;

import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.EmployeeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
@Service
public class ApplicationController {

    @Autowired
    private EmployeeFactory factory;

    public Employee createEmployee() {
        return factory.create();
    }
}
