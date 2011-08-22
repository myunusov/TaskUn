package org.maxur.taskun.datasource.hibernate;

import junit.framework.Assert;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/27/11
 */
public class EntityRepositoryImplTest {

    static private EmployeeImpl dummy = new EmployeeImpl() {
        private static final long serialVersionUID = -3552630573530150686L;
    };

    private Mockery context;

    private HibernateTemplate template;

    private EntityRepositoryImpl repository;

    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        }};
        sessionFactory = context.mock(SessionFactory.class);
        repository = new EntityRepositoryImpl(sessionFactory);
        template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
    }

    @Test
    public void testIsExist() throws Exception {
        context.checking(new Expectations() {{
            ignoring(sessionFactory);
            allowing(template).getFlushMode(); will(returnValue(HibernateAccessor.FLUSH_AUTO));
            allowing(template).setFlushMode(with(any(int.class)));
            oneOf(template).findByCriteria(with(any(DetachedCriteria.class)));
            will(returnValue(Collections.nCopies(1, new Integer(0))));
        }});
        repository.isExist(dummy, new String[]{"firstName", "lastName"});
        context.assertIsSatisfied();
    }

    @Test
    public void testIsExistWithInvalidFields() throws Exception {
        context.checking(new Expectations() {{
            ignoring(sessionFactory);
            allowing(template).getFlushMode(); will(returnValue(HibernateAccessor.FLUSH_AUTO));
            allowing(template).setFlushMode(with(any(int.class)));
            oneOf(template).findByCriteria(with(any(DetachedCriteria.class)));
            will(returnValue(Collections.nCopies(1, new Integer(0))));
        }});
        repository.isExist(dummy, new String[]{"a", "b"});
        context.assertIsSatisfied();
    }

    @Test
    public void testIsExistIfNotExist() throws Exception {
        context.checking(new Expectations() {{
            ignoring(sessionFactory);
            allowing(template).getFlushMode(); will(returnValue(HibernateAccessor.FLUSH_AUTO));
            allowing(template).setFlushMode(with(any(int.class)));
            oneOf(template).findByCriteria(with(any(DetachedCriteria.class)));
            will(returnValue(Collections.nCopies(1, new Integer(0))));
        }});
        boolean result = repository.isExist(dummy, new String[]{"firstName", "lastName"});
        context.assertIsSatisfied();
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsExistIfExist() throws Exception {
        context.checking(new Expectations() {{
            ignoring(sessionFactory);
            allowing(template).getFlushMode(); will(returnValue(HibernateAccessor.FLUSH_AUTO));
            allowing(template).setFlushMode(with(any(int.class)));
            oneOf(template).findByCriteria(with(any(DetachedCriteria.class)));
            will(returnValue(Collections.nCopies(1, new Integer(1))));
        }});
        boolean result = repository.isExist(dummy, new String[]{"firstName", "lastName"});
        context.assertIsSatisfied();
        Assert.assertEquals(true, result);
    }

    @Test(expected = AssertionError.class)
    public void testIsExistIfCountIsZero() throws Exception {
        context.checking(new Expectations() {{
            ignoring(sessionFactory);
            allowing(template).getFlushMode(); will(returnValue(HibernateAccessor.FLUSH_AUTO));
            allowing(template).setFlushMode(with(any(int.class)));
            oneOf(template).findByCriteria(with(any(DetachedCriteria.class)));
            will(returnValue(Collections.nCopies(0, new Integer(1))));
        }});
        repository.isExist(dummy, new String[]{"firstName", "lastName"});
    }

    @Test(expected = AssertionError.class)
    public void testIsExistIfCountMoreThan1() throws Exception {
        context.checking(new Expectations() {{
            ignoring(sessionFactory);
            allowing(template).getFlushMode(); will(returnValue(HibernateAccessor.FLUSH_AUTO));
            allowing(template).setFlushMode(with(any(int.class)));
            oneOf(template).findByCriteria(with(any(DetachedCriteria.class)));
            will(returnValue(Collections.nCopies(0, new Integer(2))));
        }});
        repository.isExist(dummy, new String[]{"firstName", "lastName"});
    }


    @Test
    public void testIsExistIfNotNew() throws Exception {
        context.checking(new Expectations() {{
            ignoring(sessionFactory);
            allowing(template).getFlushMode(); will(returnValue(HibernateAccessor.FLUSH_AUTO));
            allowing(template).setFlushMode(with(any(int.class)));
            oneOf(template).findByCriteria(with(any(DetachedCriteria.class)));
            will(returnValue(Collections.nCopies(1, new Integer(1))));
        }});
        dummy.setIdentifier("1");
        boolean result = repository.isExist(dummy, new String[]{"firstName", "lastName"});
        context.assertIsSatisfied();
        Assert.assertEquals(true, result);
    }

}
