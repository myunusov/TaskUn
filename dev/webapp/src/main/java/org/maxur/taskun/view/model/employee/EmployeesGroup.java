package org.maxur.taskun.view.model.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.domain.AllSpecification;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.model.Bean;
import org.maxur.taskun.view.model.ModelList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The selected group of employees.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class EmployeesGroup extends Bean<ModelList<EmployeeBean>> implements IDataProvider<EmployeeBean> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 5290886153582258354L;


    /**
     * Employees from this group.
     */
    private List<EmployeeBean> employees;


    private Specification<Employee> specification;


    /**
     * The ApplicationController bean. It's injected by Wicket IoC.
     */
    @SpringBean
    private ApplicationController controller;

    /**
     * Constructs group from employees.
     *
     */
    public EmployeesGroup() {
        super();
        specification = new AllSpecification<Employee>();
        refresh();
    }

    public void refresh(final AjaxRequestTarget target) {
        refresh();
        update(target);
    }

    private void refresh() {
        final List<Employee> list = controller.getAllEmployee(specification);
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
    public final int size() {
        return employees.size();
    }

    public Integer getSelectedCount() {
        Integer result = 0;
        for (EmployeeBean employee : employees) {
            result += employee.isSelected() ? 1 : 0;
        }
        return result;
    }

    @Override
    public Iterator<? extends EmployeeBean> iterator(int first, int count) {
        return (getObject().subList(first, first + count)).iterator();
    }

    @Override
    public IModel<EmployeeBean> model(final EmployeeBean object) {
         return object;
    }

    /**
     * Get All employees from this group.
     *
     * @return All employees from this group as list.
     */
    @Override
    public ModelList<EmployeeBean> getObject() {
        return new ModelList<EmployeeBean>(employees);
    }


    public void removeSelected(final AjaxRequestTarget target) {
        for (Iterator<EmployeeBean> iterator = employees.iterator(); iterator.hasNext(); ) {
            EmployeeBean bean = iterator.next();
            if (bean.isSelected()) {
                bean.delete();
                iterator.remove();
            }
        }
        update(target);
    }


    public void addEmployee(AjaxRequestTarget target, final EmployeeBean newEmployee) {
        //TODO May be add to group for new item
        refresh(target);
    }

}