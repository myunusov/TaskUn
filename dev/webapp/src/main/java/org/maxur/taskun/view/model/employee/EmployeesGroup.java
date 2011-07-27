package org.maxur.taskun.view.model.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.domain.AllSpecification;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.model.Bean;
import org.maxur.taskun.view.model.ModelList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

    private final Set<String> selected = new HashSet<String>();


    /**
     * The ApplicationController bean. It's injected by Wicket IoC.
     */
    @SpringBean
    private ApplicationController controller;

    /**
     * Constructs group from employees.
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
        Set<String> identifiers = new HashSet<String>();
        this.employees = new ArrayList<EmployeeBean>();
        for (final Employee employee : controller.getAllEmployee(specification)) {
            this.employees.add(0, new EmployeeBean(this, employee));
            identifiers.add(employee.getIdentifier());
        }

        for (final String id : selected) {
            if (!identifiers.contains(id)) {
                selected.remove(id);
            }
        }
    }

    /**
     * Gets total number of  Employees from this group.
     *
     * @return The total number of  Employees from this group.
     */
    public final int size() {
        return getObject().size();
    }

    public Integer getSelectedCount() {
        return selected.size();
    }

    @Override
    public Iterator<? extends EmployeeBean> iterator(int first, int count) {
        return (getObject().subList(first, first + count)).iterator();
    }

    @Override
    public IModel<EmployeeBean> model(final EmployeeBean object) {
        return new Model<EmployeeBean>(object);
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
        for (Iterator<EmployeeBean> iterator = getObject().iterator(); iterator.hasNext(); ) {
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

    public void select(final AjaxRequestTarget target, final EmployeeBean bean) {
        final String identifier = bean.getIdentifier();
        if (selected.contains(identifier)) {
            selected.remove(identifier);
        } else {
            selected.add(identifier);
        }
        update(target);
    }

    public boolean isSelected(EmployeeBean bean) {
        return selected.contains(bean.getIdentifier());
    }
}