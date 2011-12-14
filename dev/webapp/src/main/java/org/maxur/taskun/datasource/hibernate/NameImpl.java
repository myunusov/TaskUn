/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.taskun.datasource.hibernate;

import org.maxur.taskun.domain.Name;

import javax.persistence.Embeddable;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/28/11
 */
@Embeddable
public class NameImpl extends Name {

    public NameImpl() {
    }

    public NameImpl(final String value) {
        super(value);
    }

    @Override
    public void setName(final String value) {
        super.setName(value);
    }

    @Override
    public String getName() {
        return super.getName();
    }

}
