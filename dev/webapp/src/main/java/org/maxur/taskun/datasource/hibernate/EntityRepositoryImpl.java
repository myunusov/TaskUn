package org.maxur.taskun.datasource.hibernate;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.maxur.commons.annotation.Benchmark;
import org.maxur.commons.domain.Entity;
import org.maxur.commons.domain.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public final boolean isExist(final Entity entity, String[] fields) {
        ClassMetadata meta = getFactory().getClassMetadata(entity.getClass());
        String idName = meta.getIdentifierPropertyName();

        DetachedCriteria criteria = DetachedCriteria.forClass(entity.getClass());
        for (String field : fields) {
            criteria.add(Restrictions.eq(field, meta.getPropertyValue(entity, field, EntityMode.POJO)));
        }
        if (null != entity.getIdentifier()) {
            criteria.add(Restrictions.ne(idName, entity.getIdentifier()));
        }
        criteria.setProjection(Projections.rowCount());

        final int flushMode = getHibernateTemplate().getFlushMode();
        getHibernateTemplate().setFlushMode(HibernateAccessor.FLUSH_NEVER);  //prevent Stack Overflow
        final List results = getHibernateTemplate().findByCriteria(criteria);
        getHibernateTemplate().setFlushMode(flushMode);
        assert(1 == results.size()) : "SELECT COUNT(*) has return more (less) than at one record";
        final Number count = (Number) results.iterator().next();
        return count.intValue() != 0;
    }

}
