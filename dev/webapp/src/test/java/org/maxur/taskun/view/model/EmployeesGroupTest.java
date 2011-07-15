package org.maxur.taskun.view.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.AbstractEmployee;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class EmployeesGroupTest {

    private EmployeesGroup group;

    @Before
    public void setUp() throws Exception {
        group = new EmployeesGroup(Collections.<AbstractEmployee>nCopies(5, null));
    }

    @Test
    public void testGetTotal() throws Exception {
        Assert.assertEquals(new Integer(5), group.getTotal());
    }

    @Test
    public void testGetAll() throws Exception {
        Assert.assertEquals(5, group.getAll().size());
    }

    @Test
    public void testSelectedCount() throws Exception {
        Assert.assertEquals(new Integer(0), group.getSelectedCount());
    }

    @Test (expected = UnsupportedOperationException.class)
    public void testModifyEmployeeList() throws Exception {
        group.getAll().add(null);
    }


}
