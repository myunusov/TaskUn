package org.maxur.taskun.view.model;

import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.ApplicationController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The selected group of employees.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class EmployeesGroup implements Serializable {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5290886153582258354L;


    /**
     * Employees from this group.
     */
    private final List<EmployeeBean> employees;

    /**
     * Constructs group from employees.
     *
     * @param controller The Application controller.
     */
    public EmployeesGroup(final ApplicationController controller) {
        final List<Employee> list = controller.getAllEmployee();
        this.employees = new ArrayList<EmployeeBean>();
        for (Employee employee : list) {
            this.employees.add(0, new EmployeeBean(controller, employee));
        }
    }

    /**
     * Gets total number of  Employees from this group.
     *
     * @return The total number of  Employees from this group.
     */
    public final Integer getTotal() {
        return employees.size();
    }

    /**
     * Get All employees from this group.
     *
     * @return All employees from this group as list.
     */
    public List<EmployeeBean> getAll() {
        return Collections.unmodifiableList(employees);
    }

    public Integer getSelectedCount() {
        Integer result = 0;
        for (EmployeeBean employee : employees) {
            result += employee.isSelected() ? 1 : 0;
        }
        return result;
    }

    public void removeSelected() {
        for (Iterator<EmployeeBean> iterator = employees.iterator(); iterator.hasNext(); ) {
            EmployeeBean employee = iterator.next();
            if (employee.isSelected()) {
                employee.remove();
                iterator.remove();
            }
        }
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }
}
