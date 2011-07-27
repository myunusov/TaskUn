package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/27/11
 */
public class RepositoryImpl {

    private HibernateTemplate hibernateTemplate;

    private final SessionFactory factory;

    /**
     * Constructs the instance of EmployeeRepository class.
     *
     * @param sessionFactory The Hibernate Session Factory.
     */
    public RepositoryImpl(final SessionFactory sessionFactory) {
        this.factory = sessionFactory;
        this.setHibernateTemplate(new HibernateTemplate(sessionFactory));
    }

    /**
     * Setter for the HibernateTemplate. Unit Testing needs.
     *
     * @param template The SessionFactory
     */
    protected final void setHibernateTemplate(final HibernateTemplate template) {
        this.hibernateTemplate = template;
    }

    /**
     * Instance of Helper class that simplifies Hibernate data access code.
     *
     * @return The HibernateTemplate instance.
     * @see org.springframework.orm.hibernate3.HibernateTemplate
     */
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    /**
     * The Hibernate Session Factory.
     *
     * @return The Hibernate Session Factory
     */
    public SessionFactory getFactory() {
        return factory;
    }
}
