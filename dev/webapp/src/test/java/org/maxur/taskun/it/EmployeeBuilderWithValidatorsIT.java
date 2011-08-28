package org.maxur.taskun.it;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.datasource.hibernate.employee.EmployeeBuilderImpl;
import org.maxur.taskun.domain.employee.EmployeeBuilder;
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
public class EmployeeBuilderWithValidatorsIT {

    private static final String STRING50 = "STRING50";
    private static final String STRING51 = "123456789012345678901234567890123456789012345678901";

    @Autowired
    private Validator validator;

    private EmployeeBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new EmployeeBuilderImpl();
    }

    @Test
    public void testValidEmployee() throws Exception {
        //Arrange
        builder.withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName("Иванович");
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Valid EmployeeBuilder is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithoutMiddleName() throws Exception {
        //Arrange
        builder.withFirstName("Иван")
                .withLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Valid EmployeeBuilder is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongFirstName() throws Exception {
        //Arrange
        builder
                .withFirstName("STRING50")
                .withLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Valid EmployeeBuilder is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongFirstName() throws Exception {
        //Arrange
        builder
                .withFirstName(STRING51)
                .withLastName("Иванов");
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Invalid EmployeeBuilder is validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongLastName() throws Exception {
        //Arrange
        builder
                .withFirstName("Иван")
                .withLastName("STRING50");
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Valid EmployeeBuilder is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongLastName() throws Exception {
        //Arrange
        builder
                .withFirstName("Иван")
                .withLastName(STRING51);
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Invalid EmployeeBuilder is validated", 1, constraintViolations.size());
    }

    @Test
    public void testValidEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName(STRING50);
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Valid EmployeeBuilder is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testNullEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName(null);
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Valid EmployeeBuilder is not validated", 0, constraintViolations.size());
    }

    @Test
    public void testInvalidEmployeeWithLongMiddleName() throws Exception {
        //Arrange
        builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName(STRING51);
        //Assert
        final Set<ConstraintViolation<EmployeeBuilder>> constraintViolations = validator.validate(builder);
        Assert.assertEquals("Invalid EmployeeBuilder is validated", 1, constraintViolations.size());
    }


}
