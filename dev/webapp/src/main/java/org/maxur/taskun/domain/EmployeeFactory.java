package org.maxur.taskun.domain;

/**
 * The employee factory interface.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public interface EmployeeFactory {

    /**
     * The Employee Factory Method.
     * @return The Employee implementation.
     */
    Employee create();

}
