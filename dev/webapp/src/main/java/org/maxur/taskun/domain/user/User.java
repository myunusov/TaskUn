package org.maxur.taskun.domain.user;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.maxur.commons.domain.Entity;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.domain.validators.Authenticated;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/29/11
 */
@Authenticated(password = "password", username = "userName")
public interface User extends Entity {
    /**
     * Min length of employee names.
     */
    int MIN_USER_NAME_LENGTH = 1;
    /**
     * Max length of employee names.
     */
    int MAX_USER_NAME_LENGTH = 20;


    Employee getEmployee();

    @NotEmpty
    @Length(min = MIN_USER_NAME_LENGTH, max = MAX_USER_NAME_LENGTH)
    String getUserName();

    String getPassword();

    void setEmployee(Employee employee);

    void setUserName(String userName);

    void setPassword(String password);
}
