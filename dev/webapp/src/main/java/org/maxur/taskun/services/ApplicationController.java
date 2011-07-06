package org.maxur.taskun.services;

import org.maxur.taskun.domain.EmployeeRepository;
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
    private EmployeeRepository repository;


    public Employee createEmployee() {
        return factory.create();
    }

    @Transactional(readOnly = false)
    public void saveEmployee(final Employee employee) {
        repository.save(employee);
    }

    public List<Employee> getAllEmployee() {
        return repository.getAll();
    }

    public Employee getEmployee(final String id) {
        return repository.get(id);
    }

    @Transactional(readOnly = false)
    public void deleteEmployee(final Employee employee) {
        repository.delete(employee);
    }
}
