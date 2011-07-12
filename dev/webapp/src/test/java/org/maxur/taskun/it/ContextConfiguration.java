package org.maxur.taskun.it;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;

/**
 * The class declares one or more {@link org.springframework.context.annotation.Bean} methods and may be processed
 * by the Spring container to generate bean definitions and service requests for those beans
 * at runtime.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
@Configuration
public class ContextConfiguration {

    @Bean(name = "txMan1")
    @Autowired
    public HibernateTransactionManager txMan1(final SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean(name = "txMan2")
    @Autowired
    public HibernateTransactionManager txMan2(final SessionFactory sessionFactory) {
        return new FakeTransactionManager(sessionFactory);
    }


}
