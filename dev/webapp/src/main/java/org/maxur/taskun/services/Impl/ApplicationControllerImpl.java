package org.maxur.taskun.services.Impl;

import org.maxur.commons.domain.Factory;
import org.maxur.commons.domain.Repository;
import org.maxur.commons.domain.Specification;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.commons.annotation.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

/**
 * The TaskUN Application Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
@Service
public class ApplicationControllerImpl implements ApplicationController {

    private static final long serialVersionUID = -1805613937773652086L;

    /**
     * @see org.maxur.commons.domain.Factory
     */
    private Factory<Employee> employeeFactory;

    /**
     * @see org.maxur.commons.domain.Repository
     */
    private  Repository<Employee> employeeRepository;


    /**
     * Constructs the instance of ApplicationController class.
     *
     * @param factory    The Employee Factory bean.
     * @param repository The Employee Repository bean.
     */
    @Autowired
    public ApplicationControllerImpl(
            final Factory<Employee> factory,
            @Qualifier(value = "employeeRepository") final Repository<Employee> repository
    ) {
        this.employeeFactory = factory;
        this.employeeRepository = repository;
    }

    /**
     * This Method creates new instance of Employee.
     *
     * @return New instance of Employee.
     */
    @Override
    @Trace
    public final Employee createEmployee() {
        return employeeFactory.create();
    }

    /**
     * Save the Employee.
     *
     * @param employee The Employee to saveEmployee.
     */
    @Override
    @Trace
    @Transactional(readOnly = false)
    public final void saveEmployee(@Valid final Employee employee) {
        employeeRepository.save(employee);
    }

    /**
     * Get all employees.
     *
     * @return The List of Employees.
     */
    @Override
    @Trace
    @Transactional
    public final List<Employee> getAllEmployee() {
        return employeeRepository.getAll();
    }

    /**
     * Get all employees by specification.
     *
     * @param specification Some condition for select Employees.
     * @return The List of Employees.
     */
    @Override
    public List<Employee> getAllEmployee(Specification<Employee> specification) {
        return employeeRepository.getAll(specification);
    }

    /**
     * Get employees by it's identifier.
     *
     * @param id The Employees identifier.
     * @return The selected Employee.
     */
    @Override
    @Trace
    @Transactional
    public final Employee getEmployee(final String id) {
        return employeeRepository.get(id);
    }

    /**
     * Delete the employee.
     *
     * @param employee The Employee to delete.
     */
    @Override
    @Trace
    @Transactional(readOnly = false)
    public final void deleteEmployee(final Employee employee) {
        employeeRepository.delete(employee);
    }

}
