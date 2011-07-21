package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.services.TaskunServiceException;

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
public class EmployeesGroup extends Bean {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5290886153582258354L;


    /**
     * Employees from this group.
     */
    private List<EmployeeBean> employees;

    private final ApplicationController controller;

    /**
     * Constructs group from employees.
     *
     * @param controller         The Application controller.
     */
    public EmployeesGroup(ApplicationController controller) {
        super();
        this.controller = controller;
        refresh();
    }

    public void refresh(final AjaxRequestTarget target) {
        refresh();
        update(target);
    }

    private void refresh() {
        final List<Employee> list = controller.getAllEmployee();
        this.employees = new ArrayList<EmployeeBean>();
        for (Employee employee : list) {
            this.employees.add(0, new EmployeeBean(this, employee));
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

    public void removeSelected(final AjaxRequestTarget target) {
        for (Iterator<EmployeeBean> iterator = employees.iterator(); iterator.hasNext(); ) {
            EmployeeBean bean = iterator.next();
            if (bean.isSelected()) {
                controller.deleteEmployee(bean.getObject());
                iterator.remove();
            }
        }
        update(target);
    }

    public void saveEmployee(final Employee employee) throws TaskunServiceException {
        controller.saveEmployee(employee);
        //TODO May be add to group for new item
    }

    public EmployeeBean createEmployee() {
        return new EmployeeBean(this, controller.createEmployee());
    }

}