/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.commons.domain;

/**
 * The BaseEntity is base class of domain layer.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>06.07.11</pre>
 */
public abstract class BaseEntity implements Entity {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 2853087286450189484L;

    /**
     * The Employee's Id.
     */
    private String identifier;

    /**
     * Constructs a BaseEntity instance.
     * @param id The BaseEntity identifier.
     */
    public BaseEntity(final String id) {
        setIdentifier(id);
    }

    /**
     * Getter for Entity's Identifier.
     * @return The Entity's Identifier.
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Setter for Identifier.
     * @param value The Identifier.
     */
    public void setIdentifier(final String value) {
        this.identifier = value;
    }


    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Entity)) {
            return false;
        }
        final Entity entity = (Entity) obj;
        return (identifier != null && identifier.equals(entity.getIdentifier()));
    }

    /**
     * @see Object#hashCode()
     * @return  a hash code value for this object.
     */
    @Override
    public final int hashCode() {
        return identifier != null ? identifier.hashCode() : 0;
    }

}
