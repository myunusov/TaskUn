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
 * The Employee persistence class (hibernate implementation).
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
     * @return The Identifier.
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "EMPLOYEE_ID")
    @Override
    public final String getIdentifier() {
        return identifier;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getFirstName()
     * @return The Employee's First Name
     */
    @Column(name = "FIRST_NAME", nullable = false)
    @Override
    public final String getFirstName() {
        return firstName;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getLastName()
     * @return The Employee's Last Name
     */
    @Column(name = "LAST_NAME", nullable = false)
    @Override
    public final String getLastName() {
        return lastName;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getMiddleName()
     * @return The Employee's Middle Name
     */
    @Column(name = "MIDDLE_NAME")
    @Override
    public final String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for Identifier.
     * @param value The Identifier.
     */
    public final void setIdentifier(final String value) {
        this.identifier = value;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setFirstName(String)
     * @param value The Employee's First Name
     */
    @Override
    public final void setFirstName(final String value) {
        this.firstName = value;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setLastName(String)
     * @param value The Employee's Last Name
     */
    @Override
    public final void setLastName(final String value) {
        this.lastName = value;
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setMiddleName(String)
     * @param value The Employee's Middle Name
     */
    @Override
    public final void setMiddleName(@Nullable final String value) {
        this.middleName = value;
    }

    /**
     * @see Object#toString()
     * @return  a string representation of the object.
     */
    @Override
    public final String toString() {
        return "Employee{"
                + "Id='" + identifier + '\''
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", middleName='" + middleName + '\''
                + '}';
    }

    /**
     * @see Object#equals(Object)
     * @param   obj   the reference object with which to compare.
     * @return  <code>true</code> if this object is the same as the obj
     *          argument; <code>false</code> otherwise.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmployeeImpl)) {
            return false;
        }
        final EmployeeImpl employee = (EmployeeImpl) obj;
        return !(identifier != null
                ? !identifier.equals(employee.identifier)
                : employee.identifier != null);
    }

    /**
     * @see Object#hashCode()
     * @return  a hash code value for this object.
     */
    @Override
    public final int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }
}
