package org.maxur.taskun.it;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.datasource.hibernate.employee.EmployeeBuilderImpl;
import org.maxur.taskun.domain.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    private static final String STRING50 = "STRING50";
    private static final String STRING51 = "123456789012345678901234567890123456789012345678901";

    @Autowired
    private Validator validator;

    @Autowired
    private EmployeeBuilderImpl builder;

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testValidEmployee() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName("Иванович")
                .build();
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithoutMiddleName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .build();
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongFirstName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("STRING50")
                .withLastName("Иванов")
                .build();
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongFirstName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName(STRING51)
                .withLastName("Иванов")
                .build();
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Invalid employee is validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongLastName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("STRING50")
                .build();
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongLastName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName(STRING51)
                .build();
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Invalid employee is validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName(STRING50)
                .build();
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testNullEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName(null)
                .build();
        //Assert
        Assert.assertEquals("", employee.getMiddleName());
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Valid employee is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName(STRING51)
                .build();
        //Assert
        final Set<ConstraintViolation<Employee>> constraintViolations = validator.validate(employee);
        Assert.assertEquals("Invalid employee is validated", 1, constraintViolations.size());
    }


}
