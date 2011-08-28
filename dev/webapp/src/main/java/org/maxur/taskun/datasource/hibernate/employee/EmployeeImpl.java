package org.maxur.taskun.datasource.hibernate.employee;

import org.maxur.taskun.datasource.hibernate.EntityImpl;
import org.maxur.taskun.datasource.hibernate.MiddleNameImpl;
import org.maxur.taskun.datasource.hibernate.NameImpl;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.MiddleName;
import org.maxur.taskun.domain.Name;
import org.maxur.taskun.domain.employee.BaseEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@PrimaryKeyJoinColumn(name = "ENTITY_ID")
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
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "FIRST_NAME"))
    })
    public NameImpl getFirstNameImpl() {
        return (NameImpl) super.getEntity().getFirstName();
    }

    public void setFirstNameImpl(final NameImpl name) {
        super.getEntity().setFirstName(name);
    }


    /**
     * @return The Employee's Last Name
     * @see org.maxur.taskun.domain.employee.BaseEmployee#getFirstName()
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "LAST_NAME"))
    })
    public NameImpl getLastNameImpl() {
        return (NameImpl) super.getEntity().getLastName();
    }

    public void setLastNameImpl(final NameImpl name) {
        super.getEntity().setLastName(name);
    }


     /**
     * @return The Employee's Middle Name
     * @see org.maxur.taskun.domain.employee.BaseEmployee#getFirstName()
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "MIDDLE_NAME"))
    })
    public MiddleNameImpl getMiddleNameImpl() {
        return (MiddleNameImpl) super.getEntity().getMiddleName();
    }

    public void setMiddleNameImpl(final MiddleNameImpl name) {
        super.getEntity().setMiddleName(name);
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
    public Name getFirstName() {
        return super.getEntity().getFirstName();
    }

    @Override
    @Transient
    public Name getLastName() {
        return super.getEntity().getLastName();
    }

    @Override
    @Transient
    public MiddleName getMiddleName() {
        return super.getEntity().getMiddleName();
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
    public void setFirstName(final Name value) {
        super.getEntity().setFirstName(value);
    }

    @Override
    public void setLastName(final Name value) {
        super.getEntity().setLastName(value);
    }

    @Override
    public void setMiddleName(final @Nullable MiddleName value) {
        final MiddleName name = (null == value ? new MiddleNameImpl("") : value);
        super.getEntity().setMiddleName(name);
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
