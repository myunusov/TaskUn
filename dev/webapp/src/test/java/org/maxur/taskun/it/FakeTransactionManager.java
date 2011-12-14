package org.maxur.taskun.it;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/11/11
 */
public class FakeTransactionManager extends HibernateTransactionManager {

    private static final long serialVersionUID = -481264668981722130L;

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        super.doCommit(status);
    }

    @Override
    protected void doRollback(DefaultTransactionStatus status) {
        super.doRollback(status);
        System.out.println("ROLLBACK");
    }

    public FakeTransactionManager(final SessionFactory sessionFactory) {
        super(sessionFactory);
        System.out.println("COMMIT");
    }
}
