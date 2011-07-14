package org.maxur.taskun.services;

import org.maxur.taskun.domain.AbstractEmployee;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/14/11
 */
public interface ApplicationController {

    /**
     * This Method creates new instance of Employee.
     * @return New instance of Employee.
     */
    AbstractEmployee createEmployee();

    /**
     * Save the Employee.
     * @param employee The Employee to saveEmployee.
     * @throws TaskunServiceException Raise on any error.
     */
    void saveEmployee(AbstractEmployee employee) throws TaskunServiceException;

    /**
     * Get all employees.
     * @return The List of Employees.
     */
    List<AbstractEmployee> getAllEmployee();

    /**
     * Get employees by it's identifier.
     * @param id The Employees identifier.
     * @return The selected Employee.
     */
    AbstractEmployee getEmployee(String id);

   /**
     * Delete the employee.
     * @param employee The Employee to delete.
     */
    void deleteEmployee(AbstractEmployee employee);
}
