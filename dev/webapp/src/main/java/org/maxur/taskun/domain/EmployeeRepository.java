package org.maxur.taskun.domain;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
public interface EmployeeRepository {

    void save(Employee employee);

    List<Employee> getAll();

    Employee get(String id);

    void delete(Employee employee);

}
