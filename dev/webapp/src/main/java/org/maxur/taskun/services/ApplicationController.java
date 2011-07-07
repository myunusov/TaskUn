package org.maxur.taskun.services;

import org.maxur.taskun.domain.EmployeeRepository;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.EmployeeFactory;
import org.maxur.taskun.utils.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The TaskUN Application Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
@Service
@Transactional
public class ApplicationController {

    /**
     * @see EmployeeFactory
     */
    @Autowired
    private EmployeeFactory factory;

    /**
     * @see EmployeeRepository
     */
    @Autowired
    private EmployeeRepository repository;

    /**
     * This Method creates new instance of Employee.
     * @return New instance of Employee.
     */
    @Trace
    public Employee createEmployee() {
        return factory.create();
    }

    /**
     * Save the Employee.
     * @param employee The Employee to save.
     */
    @Trace
    @Transactional(readOnly = false)
    public void saveEmployee(final Employee employee) {
        repository.save(employee);
    }

    /**
     * Get all employees.
     * @return The List of Employees.
     */
    @Trace
    public List<Employee> getAllEmployee() {
        return repository.getAll();
    }

    /**
     * Get employees by it's identifier.
     * @param id The Employees identifier.
     * @return The selected Employee.
     */
    @Trace
    public Employee getEmployee(final String id) {
        return repository.get(id);
    }

    /**
     * Delete the employee.
     * @param employee The Employee to delete.
     */
    @Trace
    @Transactional(readOnly = false)
    public void deleteEmployee(final Employee employee) {
        repository.delete(employee);
    }
}
