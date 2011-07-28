package org.maxur.taskun.services;

import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.Employee;

import java.io.Serializable;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/14/11
 */
public interface ApplicationController extends Serializable {

    /**
     * This Method creates new instance of Employee.
     *
     * @return New instance of Employee.
     */
    Employee createEmployee();

    /**
     * Save the Employee.
     *
     *
     * @param employee The Employee to saveEmployee.
     * @throws TaskunServiceException Raise on any error.
     */
    void saveEmployee(Employee employee);

    /**
     * Get all employees.
     *
     * @return The List of Employees.
     */
    List<Employee> getAllEmployee();

    /**
     * Get all employees by specification.
     *
     * @param specification Some condition for select Employees.
     * @return The List of Employees.
     */
    List<Employee> getAllEmployee(Specification<Employee> specification);

    /**
     * Get employees by it's identifier.
     *
     * @param id The Employees identifier.
     * @return The selected Employee.
     */
    Employee getEmployee(String id);

    /**
     * Delete the employee.
     *
     * @param employee The Employee to delete.
     */
    void deleteEmployee(Employee employee);


}
