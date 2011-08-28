/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.taskun.domain;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/28/11
 */
public abstract class AbstractName {

    /**
     * Max length of name.
     */
    public static final int MAX_EMPLOYEE_NAME_LENGTH = 50;

    public static final Name UNKNOWN = new Name(EMPTY) {
    };

    private String name;

    public AbstractName(final String value) {
        assert null != value : "Name must not be null";
        this.name = value.toUpperCase();
    }

    protected AbstractName() {
    }

    public String asNormal() {
        return StringUtils.capitalize(this.name.toLowerCase());
    }

    protected String getName() {
        return this.name;
    }

    public void setName(final String value) {
        this.name = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractName)) return false;
        AbstractName that = (AbstractName) o;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
