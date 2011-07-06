package org.maxur.taskun.datasource.hibernate;

import com.sun.istack.Nullable;
import org.hibernate.annotations.GenericGenerator;
import org.maxur.taskun.domain.Employee;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The Employee domain class.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Entity
@Table(name = "EMPLOYEE")
@Component
public class EmployeeImpl extends Employee implements Serializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -5936703120817989967L;
    /**
     * The Employee's Id.
     */
    private String identifier;
    /**
     * The Employee's First Name.
     */
    private String firstName;
    /**
     * The Employee's Last Name.
     */
    private String lastName;
    /**
     * The Employee's Middle Name.
     */
    private String middleName;

    /**
     * @see org.maxur.taskun.domain.Employee#getIdentifier()
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "EMPLOYEE_ID")
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getFirstName()
     */
    @Column(name = "FIRST_NAME", nullable=false)
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getLastName()
     */
    @Column(name = "LAST_NAME", nullable=false)
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getMiddleName()
     */
    @Column(name = "MIDDLE_NAME")
    @Override
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for Identifier.
     * @param identifier The Identifier.
     */
    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setFirstName(String)
     */
    @Override
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setLastName(String)
     */
    @Override
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setMiddleName(String)
     */
    @Override
    public void setMiddleName(@Nullable final String middleName) {
        this.middleName = middleName;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Employee{" +
                "Id='" + identifier + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

    /**
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeeImpl)) {
            return false;
        }
        final EmployeeImpl employee = (EmployeeImpl) o;
        return !(identifier != null ? !identifier.equals(employee.identifier) : employee.identifier != null);

    }

    /**
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }
}
