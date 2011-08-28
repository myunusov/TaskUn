/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.taskun.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/28/11
 */
public class Name extends AbstractName {


    protected Name() {
    }

    public Name(final String value) {
        super(value);
    }

    @NotEmpty
    @Length(max = MAX_EMPLOYEE_NAME_LENGTH)
    public String getName() {
        return super.getName();
    }

}
