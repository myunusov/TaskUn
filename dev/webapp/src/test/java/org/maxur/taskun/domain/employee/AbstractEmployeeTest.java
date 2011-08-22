package org.maxur.taskun.domain.employee;

import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.Gender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/3/11
 */
public class AbstractEmployeeTest {

    private EmployeeBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new EmployeeBuilder();
    }

    @Test
    public void shouldBeCreatedWithoutParameters() throws Exception {
        //Act
        final Employee employee = builder.build();
        //Assert
        assertNotNull("A employee should be created without parameters", employee);
    }

    @Test
    public void shouldBeSetEmptyMiddleNameOnNull() throws Exception {
        //Act
        final Employee employee = builder
                .withMiddleName(null)
                .build();
        //Assert
        assertEquals("", employee.getMiddleName());
    }

    @Test
    public void shouldBeReturnTitleAsFirstMiddleLastName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName("Иванович")
                .build();
        //Assert
        assertEquals("The employee's title is wrong", "Иван Иванович Иванов", employee.getTitle());
    }


    @Test
    public void shouldBeReturnTitleWithoutMiddleNameAsFirstLastName() throws Exception {
        //Act
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .build();
        //Assert
        assertEquals("The employee's title is wrong", "Иван Иванов", employee.getTitle());
    }

    @Test
    public void shouldBeSetMaleGender() throws Exception {
        //Act
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .asMale()
                .build();
        //Assert
        assertEquals("The employee's gender is wrong", Gender.MALE, employee.getGender());
    }

    @Test
    public void shouldBeSetFemaleGender() throws Exception {
        //Act
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .asFemale()
                .build();
        //Assert
        assertEquals("The employee's gender is wrong", Gender.FEMALE, employee.getGender());
    }


    @Test
    public void shouldBeEqualsToSelf() throws Exception {
        //Arrange
        final Employee employee = builder.build();
        //Act
        assertTrue(employee.equals(employee));
    }

    @Test
    public void shouldBeAutoDetectGenderAsMaleByMiddleNameWithVICH() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName("Иванович")
                .build();
        //Assert
        assertEquals("Employees gender is invalid", Gender.MALE, employee.getGender());
    }

    @Test
    public void shouldBeAutoDetectGenderAsFemaleByMiddleNameWithVNA() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName("Ивановна")
                .build();
        //Assert
        assertEquals("Employees gender is invalid", Gender.FEMALE, employee.getGender());
    }

    @Test
    public void shouldBeNotAutoDetectGenderWithUnknownMiddleName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName("UNKMOWN")
                .build();
        //Assert
        assertEquals("Employees gender is invalid", Gender.UNKNOWN, employee.getGender());
    }

    @Test
    public void shouldBeNotAutoDetectGenderWithoutMiddleName() throws Exception {
        //Arrange
        final Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .build();
        //Assert
        assertEquals("Employees gender is invalid", Gender.UNKNOWN, employee.getGender());
    }

    @Test
    public void shouldBeReturnEmployeeAssString() throws Exception {
        //Arrange
        final Employee employee = builder.build();
        //Act
        final String s = employee.toString();
        //Assert
        assertNotNull("Employees toString returns null", s);
    }


}
