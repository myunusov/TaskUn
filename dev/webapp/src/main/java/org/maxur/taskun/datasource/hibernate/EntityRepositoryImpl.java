package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.maxur.commons.annotation.Benchmark;
import org.maxur.commons.domain.Entity;
import org.maxur.commons.domain.EntityBuilder;
import org.maxur.commons.domain.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
@Repository("entityRepository")
public class EntityRepositoryImpl extends RepositoryImpl implements EntityRepository {

    /**
     * Constructs the instance of EntityRepository class.
     *
     * @param sessionFactory The Hibernate Session Factory.
     */
    @Autowired
    public EntityRepositoryImpl(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    @Benchmark
    public boolean isExist(final Entity entity, String[] fields) {
        return new SelectByKeysBuilder(this.getHibernateTemplate())
                .sameAsEntity(entity)
                .isExist();
    }

    @Override
    @Benchmark
    public boolean isExistAs(EntityBuilder value, String[] fields) {
        return new SelectByKeysBuilder(this.getHibernateTemplate())
                .forClass(value.getResultClass())
                .sameAsEntity(value)
                .isExist();
    }


}
