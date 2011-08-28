/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.commons.domain;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/28/11
 */
public class EntityBuilderTest {

    private FakeEntityEntityBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new FakeEntityEntityBuilder();
    }

    @Test
    public void shouldBeReturnsNullIdentifier() throws Exception {
        assertNull("Should Be Returns Null Identifier", builder.getIdentifier());
    }

    @Test
    public void shouldBeReturnsGenericType() throws Exception {
        assertEquals("Should Be Returns Generic Type", FakeEntity.class, builder.getResultClass());
    }



    private static class FakeEntityEntityBuilder extends EntityBuilder<FakeEntity> {

        private static final long serialVersionUID = 8320459437653879254L;

        @Override
        protected FakeEntity make() {
            return null;
        }

        @Override
        public FakeEntity build() {
            return null;
        }

        @Override
        public boolean isNew() {
            return false;
        }
    }

    private static class FakeEntity implements Entity {

        @Override
        public String getIdentifier() {
            return null;
        }

        @Override
        public boolean isNew() {
            return true;
        }
    }
}
