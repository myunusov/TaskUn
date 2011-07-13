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

/**
 * The Employee persistence class (hibernate implementation).
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Entity
@Table(name = "EMPLOYEE")
@Component
public class EmployeeImpl extends Employee {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -5936703120817989967L;


    /**
     * @see Employee#getIdentifier()
     * @return The Identifier.
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
     * @param value The Identifier.
     */
    public final void setIdentifier(final String value) {
        super.setIdentifier(value);
    }


    /**
     * @see org.maxur.taskun.domain.Employee#getFirstName()
     * @return The Employee's First Name
     */
    @Column(name = "FIRST_NAME", nullable = false)
    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getLastName()
     * @return The Employee's Last Name
     */
    @Column(name = "LAST_NAME", nullable = false)
    @Override
    public String getLastName() {
        return super.getLastName();
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getMiddleName()
     * @return The Employee's Middle Name
     */
    @Column(name = "MIDDLE_NAME")
    @Override
    public String getMiddleName() {
        return super.getMiddleName();
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getGender()
     * @return The Employee's Gender.
     */
    @Column(name = "GENDER")
    @Override
    @Enumerated(value = EnumType.STRING)
    public Gender getGender() {
        return super.getGender();
    }

}
