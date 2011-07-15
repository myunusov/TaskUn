package org.maxur.taskun.view.model;

import org.maxur.taskun.domain.AbstractEmployee;

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
     * @param list The Employees List.
     */
    public EmployeesGroup(final List<AbstractEmployee> list) {
        List<EmployeeBean> result = new ArrayList<EmployeeBean>(list.size());
        for (AbstractEmployee employee : list) {
            result.add(new EmployeeBean(employee));
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
}
