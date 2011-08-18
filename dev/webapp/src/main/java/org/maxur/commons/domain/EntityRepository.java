package org.maxur.commons.domain;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/27/11
 */
public interface EntityRepository {

    boolean isExist(Entity entity, String[] fields);

}
