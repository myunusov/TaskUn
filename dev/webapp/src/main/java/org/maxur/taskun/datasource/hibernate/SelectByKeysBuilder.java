/*******************************************************************************
 * Copyright (c) 2011 by Maxim N.Yunusov. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *                   http://www.apache.org/licenses/LICENSE-2.0.txt
 ******************************************************************************/

package org.maxur.taskun.datasource.hibernate;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.maxur.commons.annotation.Unique;
import org.maxur.commons.domain.Entity;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/27/11
 */
public class SelectByKeysBuilder<T extends Entity> {

    private static final String IDENTIFIER = "identifier";

    private Class<? extends Entity> clazz;

    private String[] fields;

    private Object[] values;

    private Projection projection;

    private String idValue;

    private final HibernateTemplate hibernateTemplate;


    public SelectByKeysBuilder(final HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private void setClass(Class<? extends Entity> clazz) {
        this.clazz = clazz;
        Unique result = searchUniqueAnnotation(this.clazz);
        if (null != result) {
            fields = result.properties();
        } else {
            assert false : "A Unique annotation must be specified for class " + clazz.getName();
        }
    }

    private Unique searchUniqueAnnotation(final Class<?> clazz) {
        if (clazz == Object.class) {
            return null;
        }
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Unique) {
                return (Unique) annotation;
            }
        }
        final Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            final Unique unique = searchUniqueAnnotation(anInterface);
            if (null != unique) {
                return unique;
            }
        }
        final Class superclass = clazz.getSuperclass();
        final Unique unique = searchUniqueAnnotation(superclass);
        if (null != unique) {
            return unique;
        }
        return null;
    }

    public SelectByKeysBuilder forClass(final Class<? extends Entity> clazz) {
        setClass(clazz);
        return this;
    }

    public SelectByKeysBuilder sameAsEntity(final Entity entity) {
        if (null == clazz) {
            setClass(entity.getClass());
        }
        this.values = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            values[i] = getValueFor(entity, fields[i]);
        }
        idValue = entity.getIdentifier();
        return this;
    }

    private Object getValueFor(final Entity entity, final String field) {
        try {
            return PropertyUtils.getProperty(entity, field);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }

    public SelectByKeysBuilder byKeysValues(Object[] values) {
        this.values = new Object[values.length];
        System.arraycopy(values, 0, this.values, 0, values.length);
        return this;
    }

    public DetachedCriteria build() {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        if (null != fields && null != values) {
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i];
                Object value = values[i];
                if ("firstName".equals(field)) {         //TODO
                    field = "firstNameImpl";
                }
                if ("lastName".equals(field)) {         //TODO
                    field = "lastNameImpl";
                }
                if ("middleName".equals(field)) {         //TODO
                    field = "middleNameImpl";
                }
                criteria.add(Restrictions.eq(field, value));
            }
        }
        if (null != idValue) {
            criteria.add(Restrictions.ne(IDENTIFIER, idValue));
        }
        criteria.setProjection(projection);
        return criteria;
    }

    public boolean isExist() {
        this.projection = Projections.rowCount();
        final DetachedCriteria criteria = this.build();
        final List results = safeQuery(criteria);
        assert (1 == results.size()) : "SELECT COUNT(*) has return more (less) than at one record";
        return ((Number) results.iterator().next()).intValue() != 0;
    }

    public T getOne() {
        final DetachedCriteria criteria = this.build();
        final List<T> results = query(criteria);
        assert (1 >= results.size()) : "SELECT by business key has return more than at one record";
        return  results.size() == 0 ? null : results.iterator().next();
    }

    private List safeQuery(DetachedCriteria criteria) {
        final int flushMode = hibernateTemplate.getFlushMode();
        hibernateTemplate.setFlushMode(HibernateAccessor.FLUSH_NEVER);  //prevent Stack Overflow
        final List results;
        try {
            results = hibernateTemplate.findByCriteria(criteria);
        } finally {
            hibernateTemplate.setFlushMode(flushMode);
        }
        return results;
    }

    @SuppressWarnings({"unchecked"})
    private List<T> query(DetachedCriteria criteria) {
        return hibernateTemplate.findByCriteria(criteria);
    }

}
