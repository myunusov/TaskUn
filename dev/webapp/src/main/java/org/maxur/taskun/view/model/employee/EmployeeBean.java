package org.maxur.taskun.view.model.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.services.TaskunServiceException;
import org.maxur.taskun.view.model.Bean;

import javax.annotation.Nullable;

/**
 * The Employee class ui decorator.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class EmployeeBean extends Bean<Employee> implements Employee {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3908424889025108375L;


    /**
     * Thew group of employees.
     */
    private final EmployeesGroup owner;

    /**
     * The wrapped cached employee.
     */
    private final Employee employee;


    /**
     * The ApplicationController bean. It's injected by Wicket IoC.
     */
    @SpringBean
    private ApplicationController controller;


    /**
     * Wraps  employee with Employee Bean instance.
     *
     * @param group The owner group
     */
    public EmployeeBean(EmployeesGroup group) {
        super();
        this.owner = group;
        this.employee = controller.createEmployee();
    }


    /**
     * Wraps  employee with Employee Bean instance.
     *
     * @param group  The owner group
     * @param entity The wrapped employee.
     */
    protected EmployeeBean(final EmployeesGroup group, final Employee entity) {
        this.owner = group;
        this.employee = entity;
    }

    /**
     * Getter for the Employee's Identifier.
     *
     * @return The Employee's Identifier.
     */
    @Override
    public final String getIdentifier() {
        return getObject().getIdentifier();
    }

    /**
     * Getter for the Employee's First Name.
     *
     * @return The Employee's First Name.
     */
    @Override
    public final String getFirstName() {
        return getObject().getFirstName();
    }

    /**
     * Getter for the Employee's Last Name.
     *
     * @return The Employee's Last Name.
     */
    @Override
    public final String getLastName() {
        return getObject().getLastName();
    }

    /**
     * Getter for the Employee's Middle Name.
     *
     * @return The Employee's Middle Name.
     */
    @Override
    public final String getMiddleName() {
        return getObject().getMiddleName();
    }

    /**
     * Getter for the Employee's Title.
     *
     * @return The Employee's Title.
     */
    @Override
    public final String getTitle() {
        return getObject().getTitle();
    }

    /**
     * Getter for the Employee's Gender.
     *
     * @return The Employee's Gender.
     */
    @Override
    public final Gender getGender() {
        return getObject().getGender();
    }

    /**
     * Getter for the Employee's avatar image.
     *
     * @return The Employee's avatar image.
     */
    public final String getImageName() {
        String result = "";
        if (isNew()) {
            result = "User_new.png";
        } else {
            switch (getGender()) {
                case MALE:
                    result = "User_male.png";
                    break;
                case FEMALE:
                    result = "User_female.png";
                    break;
                case UNKNOWN:
                    result = "User_black.png";
            }
        }
        return result;
    }

    /**
     * Getter for isNew state.
     *
     * @return True if it is new employee.
     */
    public boolean isNew() {
        return null == getIdentifier();
    }


    /**
     * Getter for Selected state.
     *
     * @return True if Employee is selected.
     */
    public boolean isSelected() {
        return owner.isSelected(this);
    }

    /**
     * Setter for the Employee's First Name.
     *
     * @param value The Employee's First Name.
     */
    @Override
    public final void setFirstName(final String value) {
        getObject().setFirstName(value);
    }

    @Override
    public final void setLastName(final String value) {
        getObject().setLastName(value);
    }

    /**
     * Setter for the Employee's Last Name.
     *
     * @param value The Employee's Last Name.
     */
    @Override
    public final void setMiddleName(@Nullable final String value) {
        getObject().setMiddleName(value);
    }

    /**
     * Setter for the Employee's Gender.
     *
     * @param value The Employee's Gender.
     */
    @Override
    public final void setGender(final Gender value) {
        getObject().setGender(value);
    }

    /**
     * Switch selected state for employee.
     *
     * @param target A request target that produces ajax response.
     */
    public void select(final AjaxRequestTarget target) {
        owner.select(target, this);
    }

    /**
     * Save employee.
     *
     * @param target A request target that produces ajax response.
     * @throws TaskunServiceException Throws on any exceptions.
     */
    public void save(final AjaxRequestTarget target) throws TaskunServiceException {
        final boolean aNew = isNew();
        this.controller.saveEmployee(getObject());
        if (aNew) {
            this.owner.addEmployee(target, this);
        }
        update(target);
    }

    /**
     * Delete employee.
     */
    final void delete() {
        this.controller.deleteEmployee(getObject());
    }

    @Override
    public final Employee getObject() {
        return this.employee;
    }

    @Override
    public void detach() {
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmployeeBean)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }

        final EmployeeBean that = (EmployeeBean) obj;

        return !(null != getObject() ? !getObject().equals(that.getObject()) : null != that.getObject())
                && !(null != this.owner ? !this.owner.equals(that.owner) : null != that.owner);

    }

    @Override
    public final int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (null != this.owner ? this.owner.hashCode() : 0);
        result = 31 * result + (null != getObject() ? getObject().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EmployeeBean{" + "employee=" + this.getObject() + '}';
    }

}
