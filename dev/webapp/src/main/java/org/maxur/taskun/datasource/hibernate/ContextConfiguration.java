package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
@Configuration
public class ContextConfiguration {

    @Bean
    @Autowired
    public HibernateTemplate hibernateTemplate(final SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }
}
