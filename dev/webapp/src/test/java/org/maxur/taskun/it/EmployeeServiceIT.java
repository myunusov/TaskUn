package org.maxur.taskun.it;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.services.ApplicationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;


/**
 * Integration test of ApplicationController class.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/applicationContext-test.xml"})
public class EmployeeServiceIT {

    @Autowired
    ApplicationController controller;

    @Test
    public void testNewEmployeeNotNull() {
        Employee employee = controller.createEmployee();
        Assert.assertNotNull("New Employee is null", employee);
    }

	@Test
	public void testSaveEmployee() {
		Employee employee = controller.createEmployee();
		employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
        controller.saveEmployee(employee);
        final Collection<Employee> employees = controller.getAllEmployee();
        Assert.assertEquals(1, employees.size());
        final Employee employee1 = employees.iterator().next();
        Assert.assertEquals(employee, employee1);
        final Employee employee2 = controller.getEmployee(employee1.getIdentifier());
        Assert.assertEquals(employee, employee2);
        employee2.setMiddleName("Петрович");
        controller.saveEmployee(employee2);
        final Employee employee3 = controller.getEmployee(employee1.getIdentifier());
        Assert.assertEquals(employee, employee3);
        Assert.assertEquals("Петрович", employee3.getMiddleName());
        controller.deleteEmployee(employee3);
        final Collection<Employee> employees2 = controller.getAllEmployee();
        Assert.assertEquals(0, employees2.size());
	}

}
