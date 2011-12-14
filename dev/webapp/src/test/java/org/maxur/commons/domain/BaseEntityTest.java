package org.maxur.commons.domain;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.employee.BaseEmployee;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
public class BaseEntityTest {

    private BaseEntity entity;

    @Before
    public void setUp() {
        entity = new FakeBaseEntity();
    }

    @Test
    public void shouldBeEqualsByEqualsIdentifier() throws Exception {
        entity.setIdentifier("1");
        BaseEntity entity2 = new FakeBaseEntity();
        entity2.setIdentifier("1");
        Assert.assertEquals(entity, entity2);
    }


    @Test
    public void shouldBeNotEqualsByNullIdentifier() throws Exception {
        entity.setIdentifier(null);
        BaseEntity entity2 = new FakeBaseEntity();
        entity2.setIdentifier(null);
        Assert.assertFalse(entity.equals(entity2));
    }

    @Test
    public void shouldBeNotEqualsWithOtherClassInstance() throws Exception {
        entity.setIdentifier("1");
        BaseEntity entity2 = new BaseEmployee("") {
        };
        entity2.setIdentifier("1");
        Assert.assertEquals(entity, entity2);
    }

    @Test
    public void shouldBeNotEqualsWithOtherHierarchicInstance() throws Exception {
        entity.setIdentifier("1");
        Object entity2 = new Object();
        Assert.assertFalse(entity.equals(entity2));
    }

    @Test
    public void shouldBeWithHashCodeByIdentifier() throws Exception {
        entity.setIdentifier("1");
        Assert.assertEquals(entity.hashCode(), "1".hashCode());
    }

    @Test
    public void shouldBeWithZeroHashCodeByNullIdentifier() throws Exception {
        entity.setIdentifier(null);
        Assert.assertEquals(entity.hashCode(), 0);
    }

    @Test
    public void shouldBeSetAndGetIdentifier() throws Exception {
        entity.setIdentifier("1");
        Assert.assertEquals("1", entity.getIdentifier());
    }

    private static class FakeBaseEntity extends BaseEntity {
        public FakeBaseEntity() {
            super("");
        }

        @Override
        public boolean isNew() {
            return false;
        }
    }
}
