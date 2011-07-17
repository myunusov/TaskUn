package org.maxur.taskun.domain;

/**
 * The Employee Factory interface.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public interface EmployeeFactory {

    /**
     * The Employee Factory Method. It creates new instance of Employee.
     * @return The Employee implementation.
     */
    Employee create();

}
