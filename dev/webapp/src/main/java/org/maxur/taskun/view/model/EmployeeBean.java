package org.maxur.taskun.view.model;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.services.TaskunServiceException;

import javax.annotation.Nullable;

/**
 * The Employee class ui decorator.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class EmployeeBean extends Bean implements Employee {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3908424889025108375L;


    private final EmployeesGroup owner;
    /**
     * The wrapped employee.
     */
    private final Employee employee;

    /**
     * The employee's selected state.
     */
    private boolean selected;



    /**
     * Wraps  employee with Employee Bean instance.
     *
     * @param group      The owner group
     * @param employee   The wrapped employee.
     */
    protected EmployeeBean(final EmployeesGroup group, final Employee employee) {
        super();
        owner = group;
        this.employee = employee;
        this.selected = false;
    }

    /**
     * Getter for the Employee's Identifier.
     *
     * @return The Employee's Identifier.
     */
    @Override
    public String getIdentifier() {
        return employee.getIdentifier();
    }

    /**
     * Getter for the Employee's First Name.
     *
     * @return The Employee's First Name.
     */
    @Override
    public String getFirstName() {
        return employee.getFirstName();
    }

    /**
     * Getter for the Employee's Last Name.
     *
     * @return The Employee's Last Name.
     */
    @Override
    public String getLastName() {
        return employee.getLastName();
    }

    /**
     * Getter for the Employee's Middle Name.
     *
     * @return The Employee's Middle Name.
     */
    @Override
    public String getMiddleName() {
        return employee.getMiddleName();
    }

    /**
     * Getter for the Employee's Title.
     *
     * @return The Employee's Title.
     */
    public String getTitle() {
        return employee.getTitle();
    }

    /**
     * Getter for the Employee's Gender.
     *
     * @return The Employee's Gender.
     */
    public Gender getGender() {
        return employee.getGender();
    }

    /**
     * Getter for isNew state.
     * @return True if it is new employee.
     */
    public boolean isNew() {
        return getIdentifier() == null;
    }


    /**
     * Getter for Selected state.
     *
     * @return True if Employee is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Setter for the Employee's First Name.
     *
     * @param value The Employee's First Name.
     */
    @Override
    public void setFirstName(String value) {
        employee.setFirstName(value);
    }

    @Override
    public void setLastName(String value) {
        employee.setLastName(value);
    }

    /**
     * Setter for the Employee's Last Name.
     *
     * @param value The Employee's Last Name.
     */
    @Override
    public void setMiddleName(@Nullable String value) {
        employee.setMiddleName(value);
    }

    /**
     * Setter for the Employee's Gender.
     *
     * @param value The Employee's Gender.
     */
    @Override
    public void setGender(Gender value) {
        employee.setGender(value);
    }

    /**
     * Switch selected state for employee.
     *
     * @param target A request target that produces ajax response.
     */
    public void select(final AjaxRequestTarget target) {
        selected = !selected;
        owner.update(target);
    }

    /**
     * Save employee.
     *
     * @throws TaskunServiceException Throws on any exceptions.
     * @param target
     */
    public void save(final AjaxRequestTarget target) throws TaskunServiceException {
        owner.saveEmployee(employee);
        update(target);
    }

    Employee getObject() {
        return employee;
    }
}
