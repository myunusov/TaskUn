package org.maxur.taskun.view.model;

import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.services.TaskunServiceException;

import javax.annotation.Nullable;

/**
 * The Employee class ui decorator.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class EmployeeBean implements Employee {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3908424889025108375L;

    /**
     * The ApplicationController bean. It's injected by Wicket IoC.
     */
    private final ApplicationController controller;

    /**
     * The wrapped employee.
     */
    private final Employee employee;

    /**
     * The employee's selected state.
     */
    private boolean selected;

    /**
     * True if it is new employee.
     */
    private boolean isNew = false;

    /**
     * Wraps  employee with Employee Bean instance.
     * @param controller The Application Controller.
     * @param employee The wrapped employee.
     */
    public EmployeeBean(
            final ApplicationController controller,
            final Employee employee
    ) {
        this.controller = controller;
        this.employee = employee;
        this.selected = false;
    }

    /**
     * Constructs decorator Employee Bean on new employee.
     * @param controller The Application Controller.
     */
    public EmployeeBean(final ApplicationController controller) {
        this(controller, controller.createEmployee());
        this.isNew = true;
    }


    /**
     * Getter for the Employee's Identifier.
     * @return The Employee's Identifier.
     */
    @Override
    public String getIdentifier() {
        return employee.getIdentifier();
    }

    /**
     * Getter for the Employee's First Name.
     * @return The Employee's First Name.
     */
    @Override
    public String getFirstName() {
        return employee.getFirstName();
    }

    /**
     * Getter for the Employee's Last Name.
     * @return The Employee's Last Name.
     */
    @Override
    public String getLastName() {
        return employee.getLastName();
    }

    /**
     * Getter for the Employee's Middle Name.
     * @return The Employee's Middle Name.
     */
    @Override
    public String getMiddleName() {
        return employee.getMiddleName();
    }

    /**
     * Getter for the Employee's Title.
     * @return The Employee's Title.
     */
    public String getTitle() {
        return employee.getTitle();
    }

    /**
     * Getter for the Employee's Gender.
     * @return The Employee's Gender.
     */
    public Gender getGender() {
        return employee.getGender();
    }

    /**
     * Getter for Selected state.
     * @return True if Employee is selected.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Getter for isNew state.
     * @return True if it is new employee.
     */
    public boolean isNew() {
        return isNew;
    }

    /**
     * Setter for the Employee's First Name.
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
     * @param value The Employee's Last Name.
     */
    @Override
    public void setMiddleName(@Nullable String value) {
        employee.setMiddleName(value);
    }

    /**
     * Setter for the Employee's Gender.
     * @param value The Employee's Gender.
     */
    @Override
    public void setGender(Gender value) {
        employee.setGender(value);
    }

    /**
     * Switch selected state for employee.
     */
    public void select() {
        selected = !selected;
    }

    /**
     * Remove employee.
     */
    public void remove() {
        controller.deleteEmployee(employee);
    }

    /**
     * Save employee.
     * @throws TaskunServiceException Throws on any exceptions.
     */
    public void save() throws TaskunServiceException {
        controller.saveEmployee(employee);
    }


}
