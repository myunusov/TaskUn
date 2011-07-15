package org.maxur.taskun.view.model;

import org.maxur.taskun.domain.AbstractEmployee;
import org.maxur.taskun.domain.Gender;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class EmployeeBean implements Serializable {

    private static final long serialVersionUID = 3908424889025108375L;

    private final AbstractEmployee employee;

    private boolean selected;

    public EmployeeBean(final AbstractEmployee employee) {
        this.employee = employee;
        this.selected = false;
    }

    public String getTitle() {
        return employee.getTitle();
    }

    public Gender getGender() {
        return employee.getGender();
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = !selected;
    }
}
