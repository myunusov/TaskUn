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

    private static class FakeEntityEntityBuilder extends EntityBuilder<Entity> {

        private static final long serialVersionUID = 8320459437653879254L;

        @Override
        protected Entity make() {
            return null;
        }

        @Override
        public Entity build() {
            return null;
        }

        @Override
        public Class<? extends Entity> getResultClass() {
            return null;
        }

        @Override
        public boolean isNew() {
            return false;
        }
    }
}
