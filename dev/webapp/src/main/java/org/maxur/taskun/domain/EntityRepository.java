package org.maxur.taskun.domain;

import org.maxur.taskun.utils.Benchmark;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/27/11
 */
public interface EntityRepository {

    @Benchmark
    boolean isExist(Entity entity, String[] fields);

}
