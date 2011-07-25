package org.maxur.taskun.domain;

/**
 * This class is simple Specification implementation without any conditions.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public class AllSpecification<T extends Entity> implements Specification<T> {

    @Override
    public boolean isSatisfiedBy(T entity) {
        return true;
    }
}
