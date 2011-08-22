package org.maxur.taskun.it;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.domain.Factory;
import org.maxur.taskun.domain.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/21/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/applicationContext-test.xml"})
public class AbstractEmployeeWithValidatorsIT {

    @Autowired
    private Validator validator;

    @Autowired
    private Factory<Employee> factory;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testValidEmployee() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithoutMiddleName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithoutFirstName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Invalid employee is validated", 2, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithoutLastName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Invalid employee is validated", 2, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongFirstName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("12345678901234567890123456789012345678901234567890");
        employee.setLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongFirstName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("123456789012345678901234567890123456789012345678901");
        employee.setLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Invalid employee is validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongLastName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("12345678901234567890123456789012345678901234567890");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongLastName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("123456789012345678901234567890123456789012345678901");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Invalid employee is validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("12345678901234567890123456789012345678901234567890");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testNullEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName(null);
        //Assert
        Assert.assertEquals("", employee.getMiddleName());
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("123456789012345678901234567890123456789012345678901");
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Invalid employee is validated", 1, constraintViolations.size());
    }


}
