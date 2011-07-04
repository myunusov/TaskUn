package org.maxur.taskun.datasource;

import org.maxur.taskun.domain.Employee;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
public interface EmployeeDAO {

    public void save(Employee employee);

    public List<Employee> getAll();

    public Employee get(String id);

    public void delete(Employee employee);

}
