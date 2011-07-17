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
public class ApplicationControllerImpl implements ApplicationController {

    /**
     * @see EmployeeFactory
     */
    private final EmployeeFactory employeeFactory;

    /**
     * @see EmployeeRepository
     */
    private final EmployeeRepository employeeRepository;


    /**
     * Constructs the instance of ApplicationController class.
     *
     * @param factory The Employee Factory bean.
     * @param repository The Employee Repository bean.
     */
    @Autowired
    public ApplicationControllerImpl(
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
    @Override
    @Trace
    public final Employee createEmployee() {
        return employeeFactory.create();
    }

    /**
     * Save the Employee.
     * @param employee The Employee to saveEmployee.
     * @throws TaskunServiceException Raise on any error.
     */
    @Override
    @Trace
    @Transactional(readOnly = false)
    public final void saveEmployee(final Employee employee) throws TaskunServiceException {
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
    @Override
    @Trace
    @Transactional
    public final List<Employee> getAllEmployee() {
        return employeeRepository.getAll();
    }

    /**
     * Get employees by it's identifier.
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
     * @param employee The Employee to delete.
     */
    @Override
    @Trace
    @Transactional(readOnly = false)
    public final void deleteEmployee(final Employee employee) {
        employeeRepository.delete(employee);
    }

}
