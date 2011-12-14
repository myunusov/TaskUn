/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.commons.domain;

import com.mchange.util.AssertException;

import java.lang.reflect.ParameterizedType;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/28/11
 */
public abstract class EntityBuilder<T extends Entity> implements Entity {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -2678265243438440679L;

    /**
     * The factory method for creating Entity.
     *
     * @return new Entity.
     */
    protected abstract T make();

    /**
     * Returns a Class of build result. Its use trick with returns generic type.
     * see http://stackoverflow.com/questions/182636/how-to-determine-the-class-of-a-generic-type.
     *
     * @return  A Class of build result.
     */
    public Class<? extends Entity> getResultClass() {
        final Class<T> aClass;
        try {
            aClass = resultClass();
        } catch (Exception e) {
           throw new AssertException(e.getMessage());
        }
        return aClass;
    }

    /**
     * Builds a new class with entity interface.
     *
     * @return a new class with entity interface.
     */
    public abstract T build();

    /**
     * Return identifier of builder as identifier of entity (always null).
     * @see  Entity#getIdentifier().
     *
     * @return  a identifier of entity.
     */
    public String getIdentifier() {
        return null;
    }


    /**
     * Returns a Class of build result. Its use trick with returns generic type.
     * see http://stackoverflow.com/questions/182636/how-to-determine-the-class-of-a-generic-type.
     *
     * @return  A Class of build result.
     * @throws Exception throws on any unexpected error.
     */
    @SuppressWarnings({"unchecked"})
    public Class<T> resultClass() throws Exception {
        ParameterizedType superclass = (ParameterizedType)
                getClass().getGenericSuperclass();
        return (Class<T>) superclass.getActualTypeArguments()[0];
    }

}
