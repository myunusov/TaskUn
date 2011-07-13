package org.maxur.taskun.datasource.hibernate;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/13/11
 */
public class EmployeeImplTest {

    @Test
    public void testSetIdentifier() throws Exception {
        final EmployeeImpl employee = new EmployeeImpl();
        employee.setIdentifier("1");
        Assert.assertEquals("1", employee.getIdentifier());
    }
}
