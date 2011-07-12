package org.maxur.taskun.datasource.hibernate;

import com.sun.istack.Nullable;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.Gender;
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
     * @see Employee#getIdentifier()
     * @return The Identifier.
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "EMPLOYEE_ID")
    @Override
    public final String getIdentifier() {
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
    public final String getFirstName() {
        return super.getFirstName();
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getLastName()
     * @return The Employee's Last Name
     */
    @Column(name = "LAST_NAME", nullable = false)
    @Override
    public final String getLastName() {
        return super.getLastName();
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getMiddleName()
     * @return The Employee's Middle Name
     */
    @Column(name = "MIDDLE_NAME")
    @Override
    public final String getMiddleName() {
        return super.getMiddleName();
    }

    /**
     * @see org.maxur.taskun.domain.Employee#getGender()
     * @return The Employee's Gender.
     */
    @Column(name = "GENDER")
    @Type(type = "org.maxur.taskun.datasource.hibernate.EnumUserType",
            parameters = @Parameter(name = "type", value = "org.maxur.taskun.domain.Gender"))
    @Override
    public Gender getGender() {
        return super.getGender();
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setFirstName(String)
     * @param value The Employee's First Name
     */
    @Override
    public final void setFirstName(final String value) {
        super.setFirstName(value);
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setLastName(String)
     * @param value The Employee's Last Name
     */
    @Override
    public final void setLastName(final String value) {
        super.setLastName(value);
    }

    /**
     * @see org.maxur.taskun.domain.Employee#setMiddleName(String)
     * @param value The Employee's Middle Name
     */
    @Override
    public final void setMiddleName(@Nullable final String value) {
        super.setMiddleName(value);
    }

}
