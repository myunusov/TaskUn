package org.maxur.taskun.domain;

import java.io.Serializable;

/**
 * This interface represents the specification pattern. It is a particular software design pattern,
 * whereby business rules can be recombined by chaining the business rules together using boolean logic
 *
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public interface Specification<T extends Entity> extends Serializable {

    /**
     * Returns true if the entity satisfied by business rules (some criteria).
     * @param entity The entity for checking.
     * @return true if the entity satisfied by business rules
     */
    boolean isSatisfiedBy(T entity);
}
