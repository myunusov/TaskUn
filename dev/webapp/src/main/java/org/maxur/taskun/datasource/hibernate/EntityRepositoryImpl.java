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
        final DetachedCriteria criteria = new CriteriaBuilder()
                .forEntity(entity)
                .byFields(fields)
                .build();
        return query(criteria).intValue() != 0;
    }

    private Number query(DetachedCriteria criteria) {
        final int flushMode = getHibernateTemplate().getFlushMode();
        getHibernateTemplate().setFlushMode(HibernateAccessor.FLUSH_NEVER);  //prevent Stack Overflow
        final List results;
        try {

            results = getHibernateTemplate().findByCriteria(criteria);
        } finally {
            getHibernateTemplate().setFlushMode(flushMode);
        }
        assert (1 == results.size()) : "SELECT COUNT(*) has return more (less) than at one record";
        return (Number) results.iterator().next();
    }


    public class CriteriaBuilder {

        private Entity entity;

        private String[] fields;

        private Class<? extends Entity> clazz;

        CriteriaBuilder forClass(final Class<? extends Entity> clazz) {
            this.clazz = clazz;
            return this;
        }

        CriteriaBuilder forEntity(final Entity entity) {
            this.entity = entity;
            return this;
        }

        CriteriaBuilder byFields(final  String[] fields) {
            this.fields = fields;
            return this;
        }

        DetachedCriteria build() {
            clazz = null != clazz ? clazz : entity.getClass();
            DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
            for (String field : fields) {
                criteria.add(Restrictions.eq(field, getFieldValue(entity, field)));
            }

            String idName = getMetadata(entity).getIdentifierPropertyName();
            if (null != entity.getIdentifier()) {
                criteria.add(Restrictions.ne(idName, entity.getIdentifier()));
            }
            criteria.setProjection(Projections.rowCount());
            return criteria;
        }

        private Object getFieldValue(Entity entity, String field) {
            return getMetadata(entity).getPropertyValue(entity, field, EntityMode.POJO);
        }

        private ClassMetadata getMetadata(Entity entity) {
            return EntityRepositoryImpl.this.getFactory().getClassMetadata(entity.getClass());
        }
    }
}
