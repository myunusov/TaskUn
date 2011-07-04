package org.maxur.taskun.view;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Integration test of ApplicationController class.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/applicationContext-main.xml", "/spring/applicationContext-test.xml"})
public class ApplicationControllerTest {

    @Autowired
    ApplicationController controller;

    @Test
    public void testNewEmployeeNotNull() {
        Employee employee = controller.createEmployee();
        Assert.assertNotNull("New Employee is null", employee);
    }


}
