package org.maxur.commons.domain;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.employee.AbstractEmployee;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class AbstractEntityTest {

    private AbstractEntity entity;

    @Before
    public void setUp() {
        entity = new AbstractEntity() {
        };
    }

    @Test
    public void testEquals() throws Exception {
        entity.setIdentifier("1");
        AbstractEntity entity2 = new AbstractEntity() {
        };
        entity2.setIdentifier("1");
        Assert.assertEquals(entity, entity2);
    }


    @Test
    public void testEqualsWithNullIdentifier() throws Exception {
        entity.setIdentifier(null);
        AbstractEntity entity2 = new AbstractEntity() {
        };
        entity2.setIdentifier(null);
         Assert.assertFalse(entity.equals(entity2));
    }

    @Test
    public void testEqualsWithOtherClassInstance() throws Exception {
        entity.setIdentifier("1");
        AbstractEntity entity2 = new AbstractEmployee() {
        };
        entity2.setIdentifier("1");
        Assert.assertEquals(entity, entity2);
    }

    @Test
    public void testEqualsWithOtherHierarchicInstance() throws Exception {
        entity.setIdentifier("1");
        Object entity2 = new Object();
        Assert.assertFalse(entity.equals(entity2));
    }

    @Test
    public void testHashCode() throws Exception {
        entity.setIdentifier("1");
        Assert.assertEquals(entity.hashCode(), "1".hashCode());
    }

    @Test
    public void testGetIdentifier() throws Exception {
        entity.setIdentifier("1");
        Assert.assertEquals("1", entity.getIdentifier());
    }

}
