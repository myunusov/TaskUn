package org.maxur.taskun.datasource.hibernate;

import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.maxur.taskun.datasource.UnexpectedResultException;
import org.maxur.taskun.domain.Entity;
import org.maxur.taskun.domain.EntityRepository;
import org.maxur.taskun.utils.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
@Repository("entityRepository")
public class EntityRepositoryImpl extends RepositoryImpl implements EntityRepository {

    /**
     * Constructs the instance of EmployeeRepository class.
     *
     * @param sessionFactory The Hibernate Session Factory.
     */
    @Autowired
    public EntityRepositoryImpl(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    @Benchmark
    public final boolean isExist(final Entity entity, String[] fields) throws UnexpectedResultException {
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

        final List results = getHibernateTemplate().findByCriteria(criteria);
        if (1 != results.size()) {
            throw new UnexpectedResultException("SELECT COUNT(*) has return more (less) than at one record");
        }
        final Number count = (Number) results.iterator().next();
        return count.intValue() != 0;
    }

}
