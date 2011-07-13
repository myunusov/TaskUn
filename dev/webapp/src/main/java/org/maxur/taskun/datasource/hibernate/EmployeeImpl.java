package org.maxur.taskun.datasource.hibernate;

import org.hibernate.annotations.GenericGenerator;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.Gender;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The Employee persistence class (hibernate implementation).
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Component
@Entity
@Table(name = "EMPLOYEE",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"FIRST_NAME", "LAST_NAME", "MIDDLE_NAME"})})
public class EmployeeImpl extends Employee {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -5936703120817989967L;


    /**
     * @return The Identifier.
     * @see Employee#getIdentifier()
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "EMPLOYEE_ID")
    @Override
    public String getIdentifier() {
        return super.getIdentifier();
    }

    /**
     * Setter for Identifier.
     *
     * @param value The Identifier.
     */
    public final void setIdentifier(final String value) {
        super.setIdentifier(value);
    }


    /**
     * @return The Employee's First Name
     * @see org.maxur.taskun.domain.Employee#getFirstName()
     */
    @Column(name = "FIRST_NAME", nullable = false)
    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    /**
     * @return The Employee's Last Name
     * @see org.maxur.taskun.domain.Employee#getLastName()
     */
    @Column(name = "LAST_NAME", nullable = false)
    @Override
    public String getLastName() {
        return super.getLastName();
    }

    /**
     * @return The Employee's Middle Name
     * @see org.maxur.taskun.domain.Employee#getMiddleName()
     */
    @Column(name = "MIDDLE_NAME")
    @Override
    public String getMiddleName() {
        return super.getMiddleName();
    }

    /**
     * @return The Employee's Gender.
     * @see org.maxur.taskun.domain.Employee#getGender()
     */
    @Column(name = "GENDER")
    @Override
    @Enumerated(value = EnumType.STRING)
    public Gender getGender() {
        return super.getGender();
    }

}
