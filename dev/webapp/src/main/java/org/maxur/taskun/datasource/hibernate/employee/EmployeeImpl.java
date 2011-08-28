package org.maxur.taskun.datasource.hibernate.employee;

import org.maxur.taskun.datasource.hibernate.EntityImpl;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.employee.BaseEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * The Employee persistence class (hibernate implementation).
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Component
@Entity
@Table(
        name = "EMPLOYEE",
        uniqueConstraints =
                {@UniqueConstraint(columnNames =
                        {"FIRST_NAME", "LAST_NAME", "MIDDLE_NAME"})
                })
@PrimaryKeyJoinColumn(name="ENTITY_ID")
public class EmployeeImpl extends EntityImpl<BaseEmployee> implements Employee {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -5936703120817989967L;

    public EmployeeImpl() {
        super(new BaseEmployee(null));
        //setEntity(this.getEmployee());
    }


    /**
     * @return The Employee's First Name
     * @see org.maxur.taskun.domain.employee.BaseEmployee#getFirstName()
     */
    @Column(name = "FIRST_NAME", nullable = false)
    @Override
    public String getFirstName() {
        return super.getEntity().getFirstName();
    }

    /**
     * @return The Employee's Last Name
     * @see org.maxur.taskun.domain.employee.BaseEmployee#getLastName()
     */
    @Column(name = "LAST_NAME", nullable = false)
    @Override
    public String getLastName() {
        return super.getEntity().getLastName();
    }

    /**
     * @return The Employee's Middle Name
     * @see org.maxur.taskun.domain.employee.BaseEmployee#getMiddleName()
     */
    @Column(name = "MIDDLE_NAME", nullable = false)
    @Override
    public String getMiddleName() {
        return super.getEntity().getMiddleName();
    }

    /**
     * @return The Employee's Gender.
     * @see org.maxur.taskun.domain.employee.BaseEmployee#getGender()
     */
    @Column(name = "GENDER")
    @Override
    @Enumerated(value = EnumType.STRING)
    public Gender getGender() {
        return super.getEntity().getGender();
    }

    @Override
    @Transient
    public String getTitle() {
        return super.getEntity().getTitle();
    }

    @Override
    @Transient
    public boolean isNew() {
        return false;
    }


    @Override
    public void setFirstName(final String value) {
        super.getEntity().setFirstName(value);
    }

    @Override
    public void setLastName(final String value) {
        super.getEntity().setLastName(value);
    }

    @Override
    public void setMiddleName(final @Nullable String value) {
        super.getEntity().setMiddleName(value);
    }

    @Override
    public void setGender(final Gender value) {
        super.getEntity().setGender(value);
    }

    @Override
    public String toString() {
        return super.getEntity().toString();
    }

    @Override
    public boolean equals(final Object obj) {
        return this == obj || super.getEntity().equals(obj);
    }

    @Override
    public int hashCode() {
        return super.getEntity().hashCode();
    }


}
