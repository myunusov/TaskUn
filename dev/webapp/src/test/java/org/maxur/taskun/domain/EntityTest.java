package org.maxur.taskun.domain;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class EntityTest {

    private Entity entity;

    @Before
    public void setUp() {
        entity = new Entity() {
        };
    }

    @Test
    public void testEquals() throws Exception {
        entity.setIdentifier("1");
        Entity entity2 = new Entity() {
        };
        entity2.setIdentifier("1");
        Assert.assertEquals(entity, entity2);
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
