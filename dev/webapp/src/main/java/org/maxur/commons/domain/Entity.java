package org.maxur.commons.domain;

import java.io.Serializable;

/**
 * The Entity is base interface of domain layer.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public interface Entity extends Serializable {

    String getIdentifier();

}
