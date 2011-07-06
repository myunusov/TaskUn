package org.maxur.taskun.datasource.hibernate;

import org.hibernate.annotations.GenericGenerator;
import org.maxur.taskun.domain.Employee;

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

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "EMPLOYEE_ID")
    public String getIdentifier() {
        return identifier;
    }

    @Column(name = "FIRST_NAME", nullable=false)
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "LAST_NAME", nullable=false)
    public String getLastName() {
        return lastName;
    }

    @Column(name = "MIDDLE_NAME")
    public String getMiddleName() {
        return middleName;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Id='" + identifier + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

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

    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }
}
