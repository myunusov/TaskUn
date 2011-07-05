package org.maxur.taskun.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * The Employee domain class.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
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
    @Column(name = "employeeId")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }

    @Column(name = "firstName", nullable=false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName", nullable=false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "middleName")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    @Transient
    public String getTitle() {
        return String.format("%s %s %s", firstName, middleName, lastName);
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
        if (!(o instanceof Employee)) {
            return false;
        }
        final Employee employee = (Employee) o;
        return !(identifier != null ? !identifier.equals(employee.identifier) : employee.identifier != null);

    }

    @Override
    public int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }

}
