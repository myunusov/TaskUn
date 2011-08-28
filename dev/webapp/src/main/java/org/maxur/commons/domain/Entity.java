package org.maxur.commons.domain;

import java.io.Serializable;

/**
 * The Entity is base interface of domain layer.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public interface Entity extends Serializable {


    /**
     * Getter for the Entity's Identifier.
     *
     * @return The Entity's Identifier.
     */
    String getIdentifier();


    /**
     * Return true if Entity is new (is builder of Entity).
     *
     * @return true if Entity is new (is builder of Entity).
     */
    boolean isNew();
}
