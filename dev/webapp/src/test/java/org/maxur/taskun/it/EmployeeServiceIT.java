package org.maxur.taskun.it;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.AbstractEmployee;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.services.TaskunServiceException;
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
    ApplicationController controller;

    @Test
    public void testNewEmployeeNotNull() {
        AbstractEmployee employee = controller.createEmployee();
        Assert.assertNotNull("New Employee is null", employee);
    }

    @Test
    public void testSaveEmployee() throws Exception {
        AbstractEmployee employee = createEmployee("Иван", "Иванов", "Иванович");
        controller.saveEmployee(employee);
        final Collection<AbstractEmployee> employees = controller.getAllEmployee();
        Assert.assertEquals(1, employees.size());
        final AbstractEmployee employee1 = employees.iterator().next();
        Assert.assertEquals(employee, employee1);
        final AbstractEmployee employee2 = controller.getEmployee(employee1.getIdentifier());
        Assert.assertEquals(employee, employee2);
        employee2.setMiddleName("Петрович");
        controller.saveEmployee(employee2);
        final AbstractEmployee employee3 = controller.getEmployee(employee1.getIdentifier());
        Assert.assertEquals(employee, employee3);
        Assert.assertEquals("Петрович", employee3.getMiddleName());
        controller.deleteEmployee(employee3);
        final Collection<AbstractEmployee> employees2 = controller.getAllEmployee();
        Assert.assertEquals(0, employees2.size());
    }

    //TODO flush is make result not clean
    @Test (expected = TaskunServiceException.class)
    @Transactional
    @Rollback(true)
    public void testSaveDuplicateEmployee() throws Exception  {
        controller.saveEmployee(createEmployee("Иван", "Иванов", "Иванович"));
        controller.saveEmployee(createEmployee("Иван", "Иванов", "Иванович"));
    }

    private AbstractEmployee createEmployee(final String иван, final String иванов, final String иванович) {
        AbstractEmployee employee;
        employee = controller.createEmployee();
        employee.setFirstName(иван);
        employee.setLastName(иванов);
        employee.setMiddleName(иванович);
        return employee;
    }


}
