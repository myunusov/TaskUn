package org.maxur.taskun.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.datasource.hibernate.EmployeeFactoryImpl;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
public class EmployeeTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testCreateEmployee() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        //Act
        final Employee employee = factory.create();
        //Assert
        Assert.assertNotNull("A employee was not be created", employee);
    }

    @Test
    public void testEmployeeTitle() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        //Act
        final Employee employee = factory.create();
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
        //Assert
        Assert.assertEquals("The employee's title is wrong", "Иван Иванович Иванов", employee.getTitle());
    }

    @Test
    public void testEmployeeEquals() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final Employee employee = factory.create();
        //Act
        Assert.assertTrue(employee.equals(employee));
    }
}
