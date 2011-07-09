package org.maxur.taskun.domain;

import java.util.Collection;

/**
 * The Employee Repository interface.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
public interface EmployeeRepository {

    /**
     * Save the Employee.
     * @param employee The Employee to save.
     */
    void save(Employee employee);

    /**
     * Get all employees.
     * @return The List of Employees.
     */
    Collection<Employee> getAll();

    /**
     * Get employees by it's identifier.
     * @param id The Employees identifier.
     * @return The selected Employee.
     */
    Employee get(String id);

    /**
     * Delete the employee.
     * @param employee The Employee to delete.
     */
    void delete(Employee employee);

}
