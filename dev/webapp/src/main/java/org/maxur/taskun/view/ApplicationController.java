package org.maxur.taskun.view;

import org.maxur.taskun.datasource.EmployeeDAO;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.EmployeeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
@Service
@Transactional
public class ApplicationController {

    @Autowired
    private EmployeeFactory factory;

    @Autowired
    private EmployeeDAO dao;


    public Employee createEmployee() {
        return factory.create();
    }

    @Transactional(readOnly = false)
    public void saveEmployee(final Employee employee) {
        dao.save(employee);
    }

    public List<Employee> getAllEmployee() {
        return dao.getAll();
    }

    public Employee getEmployee(final String id) {
        return dao.get(id);
    }

    @Transactional(readOnly = false)
    public void deleteEmployee(final Employee employee) {
        dao.delete(employee);
    }
}
