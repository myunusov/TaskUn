package org.maxur.taskun.services;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.EmployeeFactory;
import org.maxur.taskun.domain.EmployeeRepository;
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
public class ApplicationController {

    /**
     * @see EmployeeFactory
     */
    private EmployeeFactory employeeFactory;

    /**
     * @see EmployeeRepository
     */
    private EmployeeRepository employeeRepository;

    /**
     * Constructs the instance of ApplicationController class.
     * It Needs for CGLIB Proxy.
     */
    public ApplicationController() {
        //TODO MY should be exclude after set aop dynamic proxy
    }


    /**
     * Constructs the instance of ApplicationController class.
     *
     * @param factory The Employee Factory bean.
     * @param repository The Employee Repository bean.
     */
    @Autowired
    public ApplicationController(
            final EmployeeFactory factory,
            final EmployeeRepository repository
    ) {
        this.employeeFactory = factory;
        this.employeeRepository = repository;
    }

    /**
     * This Method creates new instance of Employee.
     * @return New instance of Employee.
     */
    @Trace
    public Employee createEmployee() {
        return employeeFactory.create();
    }

    /**
     * Save the Employee.
     * @param employee The Employee to saveEmployee.
     * @throws TaskunServiceException Raise on any error.
     */
    @Trace
    @Transactional(readOnly = false)
    public void saveEmployee(final Employee employee) throws TaskunServiceException {
        try {
            // TODO MY needs proactive reaction on duplicate
            employeeRepository.save(employee);
        } catch (JDBCConnectionException e) {
            throw new TaskunServiceException(e);
        } catch (SQLGrammarException e) {
            throw new TaskunServiceException(e);
        } catch (ConstraintViolationException e) {
            throw new TaskunServiceException(e);
        } catch (LockAcquisitionException e) {
            throw new TaskunServiceException(e);
        } catch (GenericJDBCException e) {
            throw new TaskunServiceException(e);
        }
    }

    /**
     * Get all employees.
     * @return The List of Employees.
     */
    @Trace
    @Transactional
    public List<Employee> getAllEmployee() {
        return employeeRepository.getAll();
    }

    /**
     * Get employees by it's identifier.
     * @param id The Employees identifier.
     * @return The selected Employee.
     */
    @Trace
    @Transactional
    public Employee getEmployee(final String id) {
        return employeeRepository.get(id);
    }

    /**
     * Delete the employee.
     * @param employee The Employee to delete.
     */
    @Trace
    @Transactional(readOnly = false)
    public void deleteEmployee(final Employee employee) {
        employeeRepository.delete(employee);
    }

    /**
     * Setter for The Employee Factory object.
     * @param factory The Employee Factory object.
     */
    @Autowired
    public void setEmployeeFactory(final EmployeeFactory factory) {
        this.employeeFactory = factory;
    }

    /**
     * Setter for The Employee Repository object.
     * @param repository The Employee Repository object.
     */
    @Autowired
    public void setEmployeeRepository(final EmployeeRepository repository) {
        this.employeeRepository = repository;
    }
}
