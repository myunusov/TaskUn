/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.taskun.domain;

import org.hibernate.validator.constraints.Length;

import javax.annotation.Nullable;

import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/28/11
 */
public class MiddleName extends AbstractName {

    public static final MiddleName UNKNOWN = new MiddleName(EMPTY) {
    };

    protected MiddleName() {
    }

    public MiddleName(@Nullable final String value) {
        super(value == null ? EMPTY : value);
    }

    @Length(max = MAX_EMPLOYEE_NAME_LENGTH)
    public String getName() {
        return super.getName();
    }

    public Gender detectGenderForRU() {
        Gender result;
        if (getName().endsWith("ВИЧ")) {
            result = Gender.MALE;
        } else if (getName().endsWith("ВНА")) {
            result = Gender.FEMALE;
        } else {
            result = Gender.UNKNOWN;
        }
        return result;
    }
}
