package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * The class declares one or more {@link Bean} methods and may be processed
 * by the Spring container to generate bean definitions and service requests for those beans
 * at runtime.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
@Configuration
public class TestContextConfiguration {

    /**
     * Declares HibernateTemplate Bean.
     * @param sessionFactory The Hibernate Session Factory.
     * @return The instance of HibernateTemplate class.
     */
    @Bean
    @Autowired
    public HibernateTemplate hibernateTemplate(final SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

}
