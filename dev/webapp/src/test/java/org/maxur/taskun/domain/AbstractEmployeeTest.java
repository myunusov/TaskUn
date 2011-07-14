package org.maxur.taskun.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.datasource.hibernate.EmployeeFactoryImpl;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
public class AbstractEmployeeTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCreateEmployee() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        //Act
        final AbstractEmployee employee = factory.create();
        //Assert
        Assert.assertNotNull("A employee was not be created", employee);
    }

    @Test
    public void testEmployeeTitle() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        //Act
        final AbstractEmployee employee = factory.create();
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
        //Assert
        Assert.assertEquals("The employee's title is wrong", "Иван Иванович Иванов", employee.getTitle());
    }

    @Test
    public void testEmployeeTitleWithoutMiddleName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        //Act
        final AbstractEmployee employee = factory.create();
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        //Assert
        Assert.assertEquals("The employee's title is wrong", "Иван Иванов", employee.getTitle());
    }

    @Test
    public void testEmployeeGender() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        //Act
        final AbstractEmployee employee = factory.create();
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setGender(Gender.MALE);
        //Assert
        Assert.assertEquals("The employee's gender is wrong", Gender.MALE, employee.getGender());
    }


    @Test
    public void testEmployeeEquals() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        Assert.assertTrue(employee.equals(employee));
    }

    @Test
    public void testValidEmployee() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithoutMiddleName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithoutFirstName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 1, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithoutLastName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongFirstName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("12345678901234567890123456789012345678901234567890");
        employee.setLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongFirstName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("123456789012345678901234567890123456789012345678901");
        employee.setLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongLastName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("12345678901234567890123456789012345678901234567890");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongLastName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("123456789012345678901234567890123456789012345678901");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("12345678901234567890123456789012345678901234567890");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("123456789012345678901234567890123456789012345678901");
        //Assert
        final Set<ConstraintViolation<AbstractEmployee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 1, constraintViolations.size());
    }

    @Test
    public void testAutoDetectEmployeeGenderAsMale() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
        //Assert
        Assert.assertEquals("Employees genfer is invalid", Gender.MALE, employee.getGender());
    }

    @Test
    public void testAutoDetectEmployeeGenderAsFemale() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Ивановна");
        //Assert
        Assert.assertEquals("Employees genfer is invalid", Gender.FEMALE, employee.getGender());
    }

    @Test
    public void testAutoDetectEmployeeGenderAsUnknown() throws Exception {
        //Arrange
        EmployeeFactory factory = new EmployeeFactoryImpl();
        final AbstractEmployee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        //Assert
        Assert.assertEquals("Employees genfer is invalid", Gender.UNKNOWN, employee.getGender());
    }

}
