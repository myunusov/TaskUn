package org.maxur.taskun.domain.employee;

import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.domain.Factory;
import org.maxur.taskun.AbstractMockTest;
import org.maxur.taskun.datasource.DataSourceNotifier;
import org.maxur.taskun.datasource.hibernate.EmployeeFactoryImpl;
import org.maxur.taskun.domain.Gender;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
public class AbstractEmployeeTest extends AbstractMockTest {

    private Validator validator;

    private Factory<Employee> factory;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        final EmployeeFactoryImpl employeeFactory = new EmployeeFactoryImpl();
        final DataSourceNotifier notifier = context.mock(DataSourceNotifier.class);
        employeeFactory.setNotifier(notifier);
        factory = employeeFactory;
        context.checking(new Expectations() {{
            exactly(1).of(notifier).notifyEmployeeCreate(with(any(Class.class)));
        }});
    }

    @Test
    public void testCreateEmployee() throws Exception {
        //Act
        final Employee employee = factory.create();
        //Assert
        Assert.assertNotNull("A employee was not be created", employee);
    }

    @Test
    public void testEmployeeTitle() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
        //Assert
        Assert.assertEquals("The employee's title is wrong", "Иван Иванович Иванов", employee.getTitle());
    }

    @Test
    public void testEmployeeTitleWithoutMiddleName() throws Exception {
        //Act
        final Employee employee = factory.create();
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        //Assert
        Assert.assertEquals("The employee's title is wrong", "Иван Иванов", employee.getTitle());
    }

    @Test
    public void testEmployeeGender() throws Exception {
        //Act
        final Employee employee = factory.create();
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setGender(Gender.MALE);
        //Assert
        Assert.assertEquals("The employee's gender is wrong", Gender.MALE, employee.getGender());
    }


    @Test
    public void testEmployeeEquals() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        Assert.assertTrue(employee.equals(employee));
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

    @Test
    public void testAutoDetectEmployeeGenderAsMale() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
        //Assert
        Assert.assertEquals("Employees gender is invalid", Gender.MALE, employee.getGender());
    }

    @Test
    public void testAutoDetectEmployeeGenderAsFemale() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Ивановна");
        //Assert
        Assert.assertEquals("Employees gender is invalid", Gender.FEMALE, employee.getGender());
    }

    @Test
    public void testAutoDetectEmployeeGenderAsUnknown() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        //Assert
        Assert.assertEquals("Employees gender is invalid", Gender.UNKNOWN, employee.getGender());
    }

    @Test
    public void testAbstractEmployeetString() throws Exception {
        //Arrange
        final Employee employee = factory.create();
        //Act
        final String s = employee.toString();
        //Assert
        Assert.assertNotNull("Employees toString returns null", s);
    }


}
