package org.maxur.taskun.it;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.domain.employee.EmployeeBuilder;
import org.maxur.taskun.services.ApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


/**
 * Integration test of ApplicationController class.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/applicationContext-test.xml"})
public class EmployeeServiceIT {

    @Autowired
    EmployeeBuilder<Employee> builder;

    @Autowired
    ApplicationController controller;

    @Test
    public void testNewEmployeeNotNull() {
        Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .build();
        Assert.assertNotNull("New Employee is null", employee);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSaveEmployee() throws Exception {
        Employee employee = builder
                .withFirstName("Иван")
                .withLastName("Иванов")
                .withMiddleName("Иванович")
                .build();
        controller.saveEmployee(builder);
        final Collection<Employee> employees = controller.getAllEmployee();
        Assert.assertEquals(1, employees.size());
        final Employee employee1 = employees.iterator().next();
        Assert.assertEquals(employee.getFirstName(), employee1.getFirstName());
        Assert.assertEquals(employee.getLastName(), employee1.getLastName());
        Assert.assertEquals(employee.getMiddleName(), employee1.getMiddleName());
        final Employee employee2 = controller.getEmployee(employee1.getIdentifier());
        Assert.assertEquals(employee1, employee2);
        employee2.setMiddleName("Петрович");
        controller.saveEmployee(employee2);
        final Employee employee3 = controller.getEmployee(employee1.getIdentifier());
        Assert.assertEquals(employee1, employee3);
        Assert.assertEquals("ПЕТРОВИЧ", employee3.getMiddleName());
        controller.deleteEmployee(employee3);
        final Collection<Employee> employees2 = controller.getAllEmployee();
        Assert.assertEquals(0, employees2.size());
    }

    //TODO flush is make result not clean
/*    @Test (expected = TaskunServiceException.class)
    @Transactional
    @Rollback(true)
    public void testSaveDuplicateEmployee() throws Exception  {
        controller.saveEmployee(createEmployee("Иван", "Иванов", "Иванович"));
        controller.saveEmployee(createEmployee("Иван", "Иванов", "Иванович"));
    }*/


}
