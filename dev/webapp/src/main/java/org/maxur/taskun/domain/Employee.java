package org.maxur.taskun.domain;

import javax.persistence.Transient;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public abstract class Employee {


    public abstract String getIdentifier();

    public abstract String getFirstName();

    public abstract void setFirstName(String firstName);

    public abstract String getLastName();

    public abstract void setLastName(String lastName);

    public abstract String getMiddleName();

    public abstract void setMiddleName(String middleName);

    @Transient
    public String getTitle() {
        return String.format("%s %s %s", getFirstName(), getMiddleName(), getLastName());
    }

}
