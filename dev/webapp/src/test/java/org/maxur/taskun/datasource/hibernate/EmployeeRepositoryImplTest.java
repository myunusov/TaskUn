package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.AllSpecification;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
@RunWith(JMock.class)
public class EmployeeRepositoryImplTest {

    private Mockery context;

    static private Employee dummy = new AbstractEmployee() {
        private static final long serialVersionUID = -3552630573530150686L;
    };

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        }};

    }

    @Test
    public void testSave() throws Exception {
        final Session session = context.mock(Session.class);
        final SessionFactory sessionFactory = context.mock(SessionFactory.class);
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl(sessionFactory);
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).saveOrUpdate(dummy);
            ignoring(session);
        }});
        repository.save(dummy);
        context.assertIsSatisfied();
    }

    @Test
    public void testGetAll() throws Exception {
        final SessionFactory sessionFactory = context.mock(SessionFactory.class);
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl(sessionFactory);
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).find("from org.maxur.taskun.datasource.hibernate.EmployeeImpl");
        }});
        repository.getAll();
        context.assertIsSatisfied();
    }

    @Test
    public void testGetAllByAllSpecification() throws Exception {
        final SessionFactory sessionFactory = context.mock(SessionFactory.class);
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl(sessionFactory);
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).find("from org.maxur.taskun.datasource.hibernate.EmployeeImpl");
            will(returnValue((Object) Collections.nCopies(5, dummy)));
        }});
        final List<Employee> all = repository.getAll(new AllSpecification<Employee>());
        context.assertIsSatisfied();
        Assert.assertEquals(5, all.size());
    }

    @Test
    public void testGetAllByNoneSpecification() throws Exception {
        final SessionFactory sessionFactory = context.mock(SessionFactory.class);
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl(sessionFactory);
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).find("from org.maxur.taskun.datasource.hibernate.EmployeeImpl");
            will(returnValue((Object) Collections.nCopies(5, dummy)));
        }});
        final List<Employee> all = repository.getAll(new Specification<Employee>() {
            @Override
            public boolean isSatisfiedBy(Employee entity) {
                return false;
            }
        });
        context.assertIsSatisfied();
        Assert.assertEquals(0, all.size());
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testGetAllCheckUnmodifiableResult() throws Exception {
        final SessionFactory sessionFactory = context.mock(SessionFactory.class);
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl(sessionFactory);
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).find(with(any(String.class)));
            will(returnValue(Collections.nCopies(3, dummy)));
        }});
        final Collection<Employee> all = repository.getAll();
        all.add(dummy);
    }

    @Test
    public void testGet() throws Exception {
        final SessionFactory sessionFactory = context.mock(SessionFactory.class);
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl(sessionFactory);
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).get(EmployeeImpl.class, "1");
            will(returnValue(dummy));
        }});
        Employee result = repository.get("1");
        context.assertIsSatisfied();
        Assert.assertEquals(dummy, result);
    }

    @Test
    public void testDelete() throws Exception {
        final SessionFactory sessionFactory = context.mock(SessionFactory.class);
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl(sessionFactory);
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).delete(dummy);
        }});
        repository.delete(dummy);
        context.assertIsSatisfied();
    }
}
