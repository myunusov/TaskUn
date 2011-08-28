package org.maxur.taskun.view.model;

import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.domain.user.User;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/17/11
 */
public class UserBean extends Bean implements User {

    private static final long serialVersionUID = 2113891236018131885L;

    private Employee employee;

    private String userName;

    private String password;

    public UserBean() {
    }

    public UserBean(final Employee employee, final String username, final String password) {
        this.employee = employee;
        this.userName = username;
        this.password = password;
    }

    @Override
    public Employee getEmployee() {
        return employee;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Serializable getObject() {
        return this;
    }

    @Override
    public void setObject(Object object) {
        //TODO
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserBean)) {
            return false;
        }

        UserBean userBean = (UserBean) o;

        return !(userName != null ? !userName.equals(userBean.userName) : userBean.userName != null);

    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + userName + '\'' +
                '}';
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public boolean isNew() {
        return employee.isNew();
    }
}
