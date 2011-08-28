/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.commons.domain;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/28/11
 */
public abstract class EntityBuilder<T extends Entity> implements Entity {

    private static final long serialVersionUID = -2678265243438440679L;

    protected abstract T make();

    public abstract T build();

    public String getIdentifier() {
        return null;
    }

    public abstract Class<? extends Entity> getResultClass();
}
