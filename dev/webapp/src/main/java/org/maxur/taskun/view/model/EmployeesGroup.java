package org.maxur.taskun.view.model;

import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.ApplicationController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
        List<EmployeeBean> result = new ArrayList<EmployeeBean>();
        for (Employee employee : list) {
            result.add(new EmployeeBean(controller, employee));
        }
        this.employees = Collections.unmodifiableList(result);
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
        return employees;
    }

    public Integer getSelectedCount() {
        Integer result = 0;
        for (EmployeeBean employee : employees) {
            result += employee.isSelected() ? 1 : 0;
        }
        return result;
    }

    public void removeSelected() {
        for (EmployeeBean employee : employees) {
            if (employee.isSelected()) {
                employee.remove();
            }
        }
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }
}
